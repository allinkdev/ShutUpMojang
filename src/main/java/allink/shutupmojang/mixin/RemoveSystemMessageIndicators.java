package allink.shutupmojang.mixin;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator.Icon;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public class RemoveSystemMessageIndicators {

	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;fill(Lnet/minecraft/client/util/math/MatrixStack;IIIII)V"))
	public void fill(MatrixStack matrixStack, int x1, int y1, int x2, int y2, int color) {
		if (x1 == -4 && x2 == -2) {
			return;
		}

		DrawableHelper.fill(matrixStack, x1, y1, x2, y2, color);
	}

	@Inject(method = "drawIndicatorIcon", at = @At("HEAD"), cancellable = true)
	public void drawIndicatorIcon(MatrixStack matrices, int x, int y, Icon icon, CallbackInfo ci) {
		ci.cancel();
	}
}
