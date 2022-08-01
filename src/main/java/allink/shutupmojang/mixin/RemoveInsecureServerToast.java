package allink.shutupmojang.mixin;

import allink.shutupmojang.mixin.accessor.ToastManagerAccessor;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.toast.SystemToast.Type;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.toast.ToastManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayNetworkHandler.class)
public class RemoveInsecureServerToast {

	@Redirect(method = "onServerMetadata", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;add(Lnet/minecraft/client/toast/Toast;)V"))
	public void onServerMetadata(ToastManager instance, Toast toast) {
		if (toast.getType().equals(Type.UNSECURE_SERVER_WARNING)) {
			return;
		}

		final ToastManagerAccessor accessor = (ToastManagerAccessor) instance;

		accessor.getToastQueue().add(toast);
	}
}
