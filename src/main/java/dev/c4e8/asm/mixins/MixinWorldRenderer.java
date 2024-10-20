package dev.c4e8.asm.mixins;

import dev.c4e8.C4E8;
import dev.c4e8.core.impl.ShaderManager;
import dev.c4e8.mod.modules.impl.player.Freecam;
import dev.c4e8.mod.modules.impl.render.Shader;
import net.minecraft.client.gl.PostEffectProcessor;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import static dev.c4e8.api.utils.Wrapper.mc;
@Mixin(WorldRenderer.class)
public abstract class MixinWorldRenderer {
	@Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gl/PostEffectProcessor;render(F)V", ordinal = 0))
	void replaceShaderHook(PostEffectProcessor instance, float tickDelta) {
		ShaderManager.Shader shaders = Shader.INSTANCE.mode.getValue();
		if (Shader.INSTANCE.isOn() && mc.world != null) {
			C4E8.SHADER.setupShader(shaders, C4E8.SHADER.getShaderOutline(shaders));
		} else {
			instance.render(tickDelta);
		}
	}

	@ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;setupTerrain(Lnet/minecraft/client/render/Camera;Lnet/minecraft/client/render/Frustum;ZZ)V"), index = 3)
	private boolean renderSetupTerrainModifyArg(boolean spectator) {
		return Freecam.INSTANCE.isOn() || spectator;
	}
}
