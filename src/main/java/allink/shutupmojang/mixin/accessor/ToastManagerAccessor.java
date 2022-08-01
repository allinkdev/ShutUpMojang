package allink.shutupmojang.mixin.accessor;

import java.util.Deque;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ToastManager.class)
public interface ToastManagerAccessor {

	@Accessor("toastQueue")
	Deque<Toast> getToastQueue();
}
