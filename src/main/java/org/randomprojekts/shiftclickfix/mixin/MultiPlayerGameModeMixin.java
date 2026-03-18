package org.randomprojekts.shiftclickfix.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(MultiPlayerGameMode.class)
public class MultiPlayerGameModeMixin {
    @ModifyVariable(method = "performUseItemOn", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/player/LocalPlayer;isSecondaryUseActive()Z", ordinal = 0))
    private boolean changeFlag1(boolean value, LocalPlayer player, @Local(ordinal = 0) BlockPos blockpos) {
        return player.isSecondaryUseActive() && !player.getMainHandItem().doesSneakBypassUse(player.level(), blockpos, player);
    }
}
