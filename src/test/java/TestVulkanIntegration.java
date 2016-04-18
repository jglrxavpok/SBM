import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVulkan;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWVulkan.*;
import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.system.MemoryUtil.*;

public class TestVulkanIntegration {

    public static void main(String[] args) {
        MemoryStack stack = MemoryStack.create();
        VkInstanceCreateInfo creationInfos = VkInstanceCreateInfo.mallocStack(stack).flags(0).pNext(NULL).sType(VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO);
        PointerBuffer validationLayers = PointerBuffer.allocateDirect(1);
        validationLayers.put(MemoryUtil.memASCII("VK_LAYER_LUNARG_standard_validation"));
        validationLayers.flip();
        creationInfos.ppEnabledLayerNames(validationLayers);

        PointerBuffer instanceBuffer = PointerBuffer.allocateDirect(1);
        int err = VK10.vkCreateInstance(creationInfos, null, instanceBuffer);
        check(err);
        VkInstance instance = new VkInstance(instanceBuffer.get(), creationInfos);

        IntBuffer count = ByteBuffer.allocateDirect(4).asIntBuffer();
        count.put(1);
        count.flip();
        PointerBuffer result = PointerBuffer.allocateDirect(1);
        err = VK10.vkEnumeratePhysicalDevices(instance, count, result);
        check(err);
        VkPhysicalDevice physicalDevice = new VkPhysicalDevice(result.get(), instance);

        glfwInit();

        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(640, 480, "Test Vulkan", NULL, NULL);
        LongBuffer surfaceBuffer = ByteBuffer.allocateDirect(8).asLongBuffer();
        glfwCreateWindowSurface(instance, window, null, surfaceBuffer);
        long surface = surfaceBuffer.get();

        PointerBuffer deviceBuffer = PointerBuffer.allocateDirect(1);
        VkDeviceQueueCreateInfo.Buffer queue = VkDeviceQueueCreateInfo.mallocStack(1, stack)
                .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0)
                .queueFamilyIndex(getQueue(stack, physicalDevice, surface))
                .pQueuePriorities(stack.floats(0f));
        VkDeviceCreateInfo deviceInfos = VkDeviceCreateInfo.mallocStack(stack)
                .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0)
                .pQueueCreateInfos(queue)
                .pEnabledFeatures(null);
        err = VK10.vkCreateDevice(physicalDevice, deviceInfos, null, deviceBuffer);
        check(err);

        VkDevice device = new VkDevice(deviceBuffer.get(), physicalDevice, deviceInfos);

        VkSwapchainCreateInfoKHR swapInfos = createSwapchain(physicalDevice, surface, stack);

        LongBuffer swapChainBuffer = ByteBuffer.allocateDirect(8).asLongBuffer();
        err = KHRSwapchain.vkCreateSwapchainKHR(device, swapInfos, null, swapChainBuffer);
        check(err);


        VkShaderModuleCreateInfo shaderInfos = VkShaderModuleCreateInfo.mallocStack(stack)
                .sType(VK10.VK_STRUCTURE_TYPE_SHADER_MODULE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0);
        ByteBuffer codeBuffer = ByteBuffer.allocateDirect(TestReading.fragShaderCode.length);
        codeBuffer.put(TestReading.fragShaderCode);
        codeBuffer.flip();
        shaderInfos.pCode(codeBuffer);
        LongBuffer shaderModule = ByteBuffer.allocateDirect(8).asLongBuffer();
        err = VK10.vkCreateShaderModule(device, shaderInfos, null, shaderModule);
        check(err);

       /* glfwShowWindow(window);

        //while(glfwWindowShouldClose(window) != GLFW_TRUE) {
        //    render();
        //    glfwPollEvents();
       // }*/
        glfwDestroyWindow(window);
        KHRSurface.vkDestroySurfaceKHR(instance, surface, null);
        VK10.vkDestroyInstance(instance, null);
    }

    private static int getQueue(MemoryStack stack, VkPhysicalDevice gpu, long surface) {
        IntBuffer ip = memAllocInt(1);
        vkGetPhysicalDeviceQueueFamilyProperties(gpu, ip, null);
        VkQueueFamilyProperties.Buffer queue_props = VkQueueFamilyProperties.malloc(ip.get(0));
        vkGetPhysicalDeviceQueueFamilyProperties(gpu, ip, queue_props);
        IntBuffer supportsPresent = stack.mallocInt(queue_props.capacity());
        int graphicsQueueNodeIndex;
        int presentQueueNodeIndex;
        for ( int i = 0; i < supportsPresent.capacity(); i++ ) {
            supportsPresent.position(i);
            KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR(gpu, i, surface, supportsPresent);
        }

        // Search for a graphics and a present queue in the array of queue
        // families, try to find one that supports both
        graphicsQueueNodeIndex = Integer.MAX_VALUE;
        presentQueueNodeIndex = Integer.MAX_VALUE;
        for ( int i = 0; i < supportsPresent.capacity(); i++ ) {
            if ( (queue_props.get(i).queueFlags() & VK_QUEUE_GRAPHICS_BIT) != 0 ) {
                if ( graphicsQueueNodeIndex == Integer.MAX_VALUE ) {
                    graphicsQueueNodeIndex = i;
                }

                if ( supportsPresent.get(i) == VK_TRUE ) {
                    graphicsQueueNodeIndex = i;
                    presentQueueNodeIndex = i;
                    break;
                }
            }
        }
        return graphicsQueueNodeIndex;
    }

    private static VkSwapchainCreateInfoKHR createSwapchain(VkPhysicalDevice gpu, long surface, MemoryStack stack) {
        IntBuffer ip = ByteBuffer.allocate(4).asIntBuffer();
        // Check the surface capabilities and formats
        VkSurfaceCapabilitiesKHR surfCapabilities = VkSurfaceCapabilitiesKHR.mallocStack(stack);
        int err = KHRSurface.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(gpu, surface, surfCapabilities);
        check(err);

        err = KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(gpu, surface, ip, null);
        check(err);

        ip = ByteBuffer.allocate(4).asIntBuffer();
        IntBuffer presentModes = stack.mallocInt(ip.get(0));
        err = KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR(gpu, surface, ip, presentModes);
        check(err);

        int width = 0;
        int height = 0;

        VkExtent2D swapchainExtent = VkExtent2D.mallocStack(stack);
        // width and height are either both -1, or both not -1.
        if ( surfCapabilities.currentExtent().width() == -1 ) {
            // If the surface size is undefined, the size is set to
            // the size of the images requested.
            swapchainExtent.width(width);
            swapchainExtent.height(height);
        } else {
            // If the surface size is defined, the swap chain size must match
            swapchainExtent.set(surfCapabilities.currentExtent());
            width = surfCapabilities.currentExtent().width();
            height = surfCapabilities.currentExtent().height();
        }

        int swapchainPresentMode = KHRSurface.VK_PRESENT_MODE_FIFO_KHR;

        // Determine the number of VkImage's to use in the swap chain (we desire to
        // own only 1 image at a time, besides the images being displayed and
        // queued for display):
        int desiredNumberOfSwapchainImages = surfCapabilities.minImageCount() + 1;
        if ( (surfCapabilities.maxImageCount() > 0) &&
                (desiredNumberOfSwapchainImages > surfCapabilities.maxImageCount()) ) {
            // Application must settle for fewer images than desired:
            desiredNumberOfSwapchainImages = surfCapabilities.maxImageCount();
        }

        int preTransform;
        if ( (surfCapabilities.supportedTransforms() & KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR) != 0 ) {
            preTransform = KHRSurface.VK_SURFACE_TRANSFORM_IDENTITY_BIT_KHR;
        } else {
            preTransform = surfCapabilities.currentTransform();
        }

        VkSurfaceFormatKHR.Buffer surfFormats = VkSurfaceFormatKHR.mallocStack(ip.get(0), stack);

        ip = ByteBuffer.allocate(4).asIntBuffer();
        err = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(gpu, surface, ip, surfFormats);
        check(err);

        VkSwapchainCreateInfoKHR swapchain = VkSwapchainCreateInfoKHR.mallocStack(stack)
                .sType(KHRSwapchain.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR)
                .surface(surface)
                .minImageCount(desiredNumberOfSwapchainImages)
                .imageFormat(surfFormats.get(0).format())
                .imageColorSpace(surfFormats.get(0).colorSpace())
                .imageExtent(swapchainExtent)
                .imageArrayLayers(1)
                .imageUsage(VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT)
                .imageSharingMode(VK_SHARING_MODE_EXCLUSIVE)
                .preTransform(preTransform)
                .compositeAlpha(KHRSurface.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR)
                .presentMode(swapchainPresentMode)
                .clipped(VK_TRUE)
                .oldSwapchain(0);
        return swapchain;
    }

    private static void render() {

    }

    private static void check(int errcode) {
        if ( errcode != 0 )
            throw new IllegalStateException(VKUtil.translateVulkanResult(errcode));
    }
}
