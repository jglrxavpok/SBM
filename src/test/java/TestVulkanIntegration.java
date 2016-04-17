import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.vulkan.*;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public class TestVulkanIntegration {

    public static void main(String[] args) {
        VkInstanceCreateInfo creationInfos = VkInstanceCreateInfo.create();
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

        MemoryStack stack = MemoryStack.create();
        PointerBuffer deviceBuffer = PointerBuffer.allocateDirect(1);
        VkDeviceQueueCreateInfo.Buffer queue = VkDeviceQueueCreateInfo.mallocStack(1, stack)
                .sType(VK10.VK_STRUCTURE_TYPE_DEVICE_QUEUE_CREATE_INFO)
                .pNext(MemoryUtil.NULL)
                .flags(0)
                .queueFamilyIndex(0)
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



        VK10.vkDestroyInstance(instance, null);
    }

    private static void check(int errcode) {
        if ( errcode != 0 )
            throw new IllegalStateException(VKUtil.translateVulkanResult(errcode));
    }
}
