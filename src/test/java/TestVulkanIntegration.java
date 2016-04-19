import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFWVulkan.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.vulkan.EXTDebugReport.*;
import static org.lwjgl.vulkan.KHRSurface.vkGetPhysicalDeviceSurfacePresentModesKHR;
import static org.lwjgl.vulkan.KHRSwapchain.VK_IMAGE_LAYOUT_PRESENT_SRC_KHR;
import static org.lwjgl.vulkan.VK10.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * LOTS of code taken from LWJGL3 HelloVulkan example
 */
public class TestVulkanIntegration {

    private static final ByteBuffer KHR_swapchain    = memASCII(KHRSwapchain.VK_KHR_SWAPCHAIN_EXTENSION_NAME);
    private static final ByteBuffer KHR_surface    = memASCII(KHRSurface.VK_KHR_SURFACE_EXTENSION_NAME);
    private static final ByteBuffer EXT_debug_report = memASCII(EXTDebugReport.VK_EXT_DEBUG_REPORT_EXTENSION_NAME);
    private static VkQueueFamilyProperties.Buffer queue_props;

    private static final VkDebugReportCallbackEXT dbgFunc = new VkDebugReportCallbackEXT() {
        @Override
        public int invoke(int flags, int objectType, long object, long location, int messageCode, long pLayerPrefix, long pMessage, long pUserData) {

            if ( (flags & VK_DEBUG_REPORT_ERROR_BIT_EXT) != 0 ) {
                System.err.format("ERROR: [%s] Code %d : %s (%s, %s) object:(%s, %s)\n", pLayerPrefix, messageCode, getString(pMessage), location, pUserData, object, objectType);
            } else if ( (flags & VK_DEBUG_REPORT_WARNING_BIT_EXT) != 0 ) {
                System.err.format("WARNING: [%s] Code %d : %s (%s, %s) object:(%s, %s)\n", pLayerPrefix, messageCode, getString(pMessage), location, pUserData, object, objectType);
            } else {
                System.err.format("INFO: [%s] Code %d : %s (%s, %s) object:(%s, %s)\n", pLayerPrefix, messageCode, getString(pMessage), location, pUserData, object, objectType);
            }

            /*
			 * false indicates that layer should not bail-out of an
			 * API call that had validation failures. This may mean that the
			 * app dies inside the driver due to invalid parameter(s).
			 * That's what would happen without validation layers, so we'll
			 * keep that behavior here.
			 */
            return VK_FALSE;
        }
    };
    private static int queueIndex;
    private static LongBuffer longHandle;
    private static long commandPoolPointer;
    private static PointerBuffer pointerHandle;
    private static VkCommandBuffer commandBuffer;
    private static IntBuffer intHandle;
    private static SwapchainBuffers[] buffers;
    private static int format;

    public static void main(String[] args) {
        MemoryStack stack = MemoryStack.create();
        glfwInit();
        VkInstanceCreateInfo creationInfos = VkInstanceCreateInfo.mallocStack(stack)
                .flags(0)
                .pNext(NULL)
                .sType(VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO);
        PointerBuffer validationLayers = stack.mallocPointer(2);
        validationLayers
                .put(memASCII("VK_LAYER_LUNARG_core_validation"))
                /*.put(memASCII("VK_LAYER_LUNARG_device_limits"))
                .put(memASCII("VK_LAYER_LUNARG_image"))
                .put(memASCII("VK_LAYER_LUNARG_object_tracker"))
                .put(memASCII("VK_LAYER_LUNARG_parameter_validation"))
                .put(memASCII("VK_LAYER_LUNARG_swapchain"))
                .put(memASCII("VK_LAYER_GOOGLE_threading"))*/
                .put(memASCII("VK_LAYER_GOOGLE_unique_objects"));
        validationLayers.flip();

        PointerBuffer extensions = stack.mallocPointer(64);
        extensions.put(glfwGetRequiredInstanceExtensions());
        //extensions.put(KHR_surface);
        extensions.put(EXT_debug_report);
        extensions.flip();
        creationInfos.ppEnabledExtensionNames(extensions);
        creationInfos.ppEnabledLayerNames(validationLayers);

        VkDebugReportCallbackCreateInfoEXT dbgCreateInfo = null;
        dbgCreateInfo = VkDebugReportCallbackCreateInfoEXT.mallocStack(stack)
                .sType(VK_STRUCTURE_TYPE_DEBUG_REPORT_CALLBACK_CREATE_INFO_EXT)
                .pNext(NULL)
                .flags(/*VK_DEBUG_REPORT_INFORMATION_BIT_EXT | VK_DEBUG_REPORT_ERROR_BIT_EXT | VK_DEBUG_REPORT_WARNING_BIT_EXT*/0xFFFFFFFF)
                .pfnCallback(dbgFunc)
                .pUserData(NULL);


        PointerBuffer instanceBuffer = stack.mallocPointer(1);
        int err = VK10.vkCreateInstance(creationInfos, null, instanceBuffer);
        check(err);


        VkInstance instance = new VkInstance(instanceBuffer.get(0), creationInfos);
        LongBuffer buf = memAllocLong(1);
        vkCreateDebugReportCallbackEXT(instance, dbgCreateInfo, null, buf);

        IntBuffer count = stack.mallocInt(1);
        err = VK10.vkEnumeratePhysicalDevices(instance, count, null);
        check(err);

        System.out.println(">> physical device count: "+count.get(0));

        PointerBuffer result = stack.mallocPointer(count.get(0));
        err = VK10.vkEnumeratePhysicalDevices(instance, count, result);
        check(err);

        VkPhysicalDevice gpu = new VkPhysicalDevice(result.get(), instance);

        VkPhysicalDeviceProperties props = VkPhysicalDeviceProperties.mallocStack(stack);
        vkGetPhysicalDeviceProperties(gpu, props);
        System.out.println("GPU name: "+props.deviceNameString());
        System.out.println("GPU type: "+props.deviceType());
        System.out.println("GPU ID: "+props.deviceID());

        IntBuffer ip = stack.mallocInt(1);
        vkGetPhysicalDeviceQueueFamilyProperties(gpu, ip, null);
        queue_props = VkQueueFamilyProperties.malloc(ip.get(0));
        vkGetPhysicalDeviceQueueFamilyProperties(gpu, count, queue_props);

        glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        long window = glfwCreateWindow(640, 480, "Test Vulkan", NULL, NULL);
        LongBuffer surfaceBuffer = stack.mallocLong(1);
        err = glfwCreateWindowSurface(instance, window, null, surfaceBuffer);
        check(err);

        long surface = surfaceBuffer.get(0);

        System.out.println("Surface pointer: "+surface);
        System.out.println("GPU pointer: "+gpu.address());
        System.out.println("Instance pointer: "+instance.address());

        queueIndex = getQueue(stack, gpu, surface);

        PointerBuffer deviceBuffer = stack.mallocPointer(1);
        VkDeviceQueueCreateInfo.Buffer queue = VkDeviceQueueCreateInfo.mallocStack(1, stack)
                .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0)
                .queueFamilyIndex(queueIndex)
                .pQueuePriorities(stack.floats(0f));

        PointerBuffer deviceExtensions = stack.mallocPointer(1);
        deviceExtensions.put(KHR_swapchain);
        deviceExtensions.flip();

        VkDeviceCreateInfo deviceInfos = VkDeviceCreateInfo.mallocStack(stack)
                .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0)
                .pQueueCreateInfos(queue)
                .ppEnabledExtensionNames(deviceExtensions)
                .pEnabledFeatures(null)
                .ppEnabledLayerNames(validationLayers);
        err = VK10.vkCreateDevice(gpu, deviceInfos, null, deviceBuffer);
        check(err);

        VkDevice device = new VkDevice(deviceBuffer.get(), gpu, deviceInfos);

        System.out.println("Device pointer: "+device.address());

        VkSwapchainCreateInfoKHR swapInfos = createSwapchain(gpu, surface, stack);

        LongBuffer swapChainBuffer = stack.mallocLong(1);
        err = KHRSwapchain.vkCreateSwapchainKHR(device, swapInfos, null, swapChainBuffer);
        check(err);

        longHandle = memAllocLong(1);
        intHandle = memAllocInt(1);
        pointerHandle = memAllocPointer(1);
        prepare(device, swapChainBuffer.get(0));

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

        glfwShowWindow(window);

        while(glfwWindowShouldClose(window) != GLFW_TRUE) {
            render();
            glfwPollEvents();
        }
        glfwDestroyWindow(window);
        KHRSurface.vkDestroySurfaceKHR(instance, surface, null);
        VK10.vkDestroyInstance(instance, null);
    }

    private static void prepare(VkDevice device, long swapchain) {
        MemoryStack stack = MemoryStack.stackPush();
        try {
            prepareCmdBuffer(device);
            prepareSwapchainImages(device, swapchain);
        } finally {
            stack.pop();
        }
    }

    private static void prepareSwapchainImages(VkDevice device, long swapchain) {
        MemoryStack stack = MemoryStack.stackPush();
        try {
            int err = KHRSwapchain.vkGetSwapchainImagesKHR(device, swapchain, intHandle, null);
            check(err);
            int count = intHandle.get(0);
            LongBuffer images = stack.mallocLong(count);

            err = KHRSwapchain.vkGetSwapchainImagesKHR(device, swapchain, intHandle, images);
            check(err);

            buffers = new SwapchainBuffers[count];

            for(int i = 0;i < count;i++) {
                SwapchainBuffers buf = new SwapchainBuffers();
                buffers[i] = buf;
                long image = images.get(i);
                buf.image = image;

                demo_set_image_layout(
                        image, VK_IMAGE_ASPECT_COLOR_BIT,
                        VK_IMAGE_LAYOUT_UNDEFINED, VK_IMAGE_LAYOUT_PRESENT_SRC_KHR);

                buf.view = createColorAttachmentView(stack, image, device);
            }
        } finally {
            stack.pop();
        }
    }

    private static long createColorAttachmentView(MemoryStack stack, long image, VkDevice device) {
        VkImageViewCreateInfo color_attachment_view = VkImageViewCreateInfo.mallocStack(stack)
                .sType(VK_STRUCTURE_TYPE_IMAGE_VIEW_CREATE_INFO)
                .pNext(NULL)
                .flags(0)
                .image(image)
                .viewType(VK_IMAGE_VIEW_TYPE_2D)
                .format(format);

        color_attachment_view.components()
                .r(VK_COMPONENT_SWIZZLE_R)
                .g(VK_COMPONENT_SWIZZLE_G)
                .b(VK_COMPONENT_SWIZZLE_B)
                .a(VK_COMPONENT_SWIZZLE_A);

        color_attachment_view.subresourceRange()
                .aspectMask(VK_IMAGE_ASPECT_COLOR_BIT)
                .baseMipLevel(0)
                .levelCount(1)
                .baseArrayLayer(0)
                .layerCount(1);

        int err = vkCreateImageView(device, color_attachment_view, null, longHandle);
        check(err);
        return longHandle.get(0);
    }

    private static void demo_set_image_layout(long image, int aspectMask, int old_image_layout, int new_image_layout) {
        MemoryStack stack = stackPush();
        try {
            VkImageMemoryBarrier.Buffer image_memory_barrier = VkImageMemoryBarrier.mallocStack(1, stack)
                    .sType(VK_STRUCTURE_TYPE_IMAGE_MEMORY_BARRIER)
                    .pNext(NULL)
                    .srcAccessMask(0)
                    .dstAccessMask(0)
                    .oldLayout(old_image_layout)
                    .newLayout(new_image_layout)
                    .srcQueueFamilyIndex(0)
                    .dstQueueFamilyIndex(0)
                    .image(image);

            image_memory_barrier.subresourceRange()
                    .aspectMask(aspectMask)
                    .baseMipLevel(0)
                    .levelCount(1)
                    .baseArrayLayer(0)
                    .layerCount(1);

            if ( new_image_layout == VK_IMAGE_LAYOUT_TRANSFER_DST_OPTIMAL ) {
			/* Make sure anything that was copying from this image has completed */
                image_memory_barrier.dstAccessMask(VK_ACCESS_TRANSFER_READ_BIT);
            }

            if ( new_image_layout == VK_IMAGE_LAYOUT_COLOR_ATTACHMENT_OPTIMAL ) {
                image_memory_barrier.dstAccessMask(VK_ACCESS_COLOR_ATTACHMENT_WRITE_BIT);
            }

            if ( new_image_layout == VK_IMAGE_LAYOUT_DEPTH_STENCIL_ATTACHMENT_OPTIMAL ) {
                image_memory_barrier.dstAccessMask(VK_ACCESS_DEPTH_STENCIL_ATTACHMENT_WRITE_BIT);
            }

            if ( new_image_layout == VK_IMAGE_LAYOUT_SHADER_READ_ONLY_OPTIMAL ) {
			/* Make sure any Copy or CPU writes to image are flushed */
                image_memory_barrier.dstAccessMask(VK_ACCESS_SHADER_READ_BIT | VK_ACCESS_INPUT_ATTACHMENT_READ_BIT);
            }

            int src_stages = VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;
            int dest_stages = VK_PIPELINE_STAGE_TOP_OF_PIPE_BIT;

            vkCmdPipelineBarrier(commandBuffer, src_stages, dest_stages, 0, null, null, image_memory_barrier);
        } finally {
            stack.pop();
        }
    }

    private static void prepareCmdBuffer(VkDevice device) {
        MemoryStack stack = MemoryStack.stackPush();
        try {
            VkCommandPoolCreateInfo infos = VkCommandPoolCreateInfo.mallocStack(stack);
            infos.flags(0);
            infos.queueFamilyIndex(queueIndex);
            infos.pNext(NULL);
            infos.sType(VK_STRUCTURE_TYPE_COMMAND_POOL_CREATE_INFO);

            int err = vkCreateCommandPool(device, infos, null, longHandle);
            check(err);
            commandPoolPointer = longHandle.get(0);

            VkCommandBufferAllocateInfo cmd = VkCommandBufferAllocateInfo.mallocStack(stack)
                    .sType(VK_STRUCTURE_TYPE_COMMAND_BUFFER_ALLOCATE_INFO)
                    .pNext(NULL)
                    .commandPool(commandPoolPointer)
                    .level(VK_COMMAND_BUFFER_LEVEL_PRIMARY)
                    .commandBufferCount(1);

            err = vkAllocateCommandBuffers(device, cmd, pointerHandle);
            check(err);

            commandBuffer = new VkCommandBuffer(pointerHandle.get(), device);

            VkCommandBufferBeginInfo beginInfos = VkCommandBufferBeginInfo.mallocStack(stack);
            beginInfos.sType(VK_STRUCTURE_TYPE_COMMAND_BUFFER_BEGIN_INFO);
            beginInfos.flags(0);
            beginInfos.pNext(NULL);
            beginInfos.pInheritanceInfo(VkCommandBufferInheritanceInfo.mallocStack(stack)
                    .sType(VK_STRUCTURE_TYPE_COMMAND_BUFFER_INHERITANCE_INFO)
                    .pNext(NULL)
                    .renderPass(VK_NULL_HANDLE)
                    .subpass(0)
                    .framebuffer(VK_NULL_HANDLE)
                    .occlusionQueryEnable(VK_FALSE)
                    .queryFlags(0)
                    .pipelineStatistics(0));
            err = vkBeginCommandBuffer(commandBuffer, beginInfos);
            check(err);
        } finally {
            stack.pop();
        }
    }

    private static int getQueue(MemoryStack stack, VkPhysicalDevice gpu, long surface) {
        IntBuffer supportsPresent = stack.mallocInt(queue_props.capacity());
        int graphicsQueueNodeIndex;
        int presentQueueNodeIndex;
        for (int i = 0; i < supportsPresent.capacity(); i++ ) {
            supportsPresent.position(i);
            System.out.println("index: "+i);
            KHRSurface.vkGetPhysicalDeviceSurfaceSupportKHR(gpu, i, surface, supportsPresent);
        }

        // Search for a graphics and a present queue in the array of queue
        // families, try to find one that supports both
        graphicsQueueNodeIndex = Integer.MAX_VALUE;
        presentQueueNodeIndex = Integer.MAX_VALUE;
        for (int i = 0; i < supportsPresent.capacity(); i++ ) {
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

    // from LWJGL3 HelloVulkan
    private static VkSwapchainCreateInfoKHR createSwapchain(VkPhysicalDevice gpu, long surface, MemoryStack stack) {
        IntBuffer ip = stack.mallocInt(1);
        // Check the surface capabilities and formats
        VkSurfaceCapabilitiesKHR surfCapabilities = VkSurfaceCapabilitiesKHR.mallocStack(stack);
        int err = KHRSurface.vkGetPhysicalDeviceSurfaceCapabilitiesKHR(gpu, surface, surfCapabilities);
        check(err);

        err = vkGetPhysicalDeviceSurfacePresentModesKHR(gpu, surface, ip, null);
        check(err);

        IntBuffer presentModes = stack.mallocInt(ip.get(0));
        err = vkGetPhysicalDeviceSurfacePresentModesKHR(gpu, surface, ip, presentModes);
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

        err = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(gpu, surface, ip, null);
        check(err);
        VkSurfaceFormatKHR.Buffer surfFormats = VkSurfaceFormatKHR.mallocStack(ip.get(0), stack);

        err = KHRSurface.vkGetPhysicalDeviceSurfaceFormatsKHR(gpu, surface, ip, surfFormats);
        check(err);

        if ( ip.get(0) == 1 && surfFormats.get(0).format() == VK_FORMAT_UNDEFINED ) {
            format = VK_FORMAT_B8G8R8A8_UNORM;
        } else {
            assert ip.get(0) >= 1;
            format = surfFormats.get(0).format();
        }

        int colorSpace = surfFormats.get(0).colorSpace();
        VkSwapchainCreateInfoKHR swapchain = VkSwapchainCreateInfoKHR.callocStack(stack)
                .sType(KHRSwapchain.VK_STRUCTURE_TYPE_SWAPCHAIN_CREATE_INFO_KHR)
                .surface(surface)
                .minImageCount(desiredNumberOfSwapchainImages)
                .imageFormat(format)
                .imageColorSpace(colorSpace)
                .imageExtent(swapchainExtent)
                .imageArrayLayers(1)
                .imageUsage(VK_IMAGE_USAGE_COLOR_ATTACHMENT_BIT)
                .imageSharingMode(VK_SHARING_MODE_EXCLUSIVE)
                .preTransform(preTransform)
                .compositeAlpha(KHRSurface.VK_COMPOSITE_ALPHA_OPAQUE_BIT_KHR)
                .presentMode(swapchainPresentMode)
                .clipped(VK_TRUE)
                .oldSwapchain(NULL);
        return swapchain;
    }

    private static void render() {

    }

    private static void check(int errcode) {
        if ( errcode != 0 )
            throw new IllegalStateException(VKUtil.translateVulkanResult(errcode));
    }

    private static class SwapchainBuffers {
        long            image;
        VkCommandBuffer cmd;
        long            view;
    }
}
