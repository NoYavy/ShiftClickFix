package org.randomprojekts.shiftclickfix.mixin;


import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerPlayerGameMode;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(ServerPlayerGameMode.class)
public class ServerPlayerGameModeMixin {
    @ModifyVariable(method = "useItemOn", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/level/ServerPlayer;isSecondaryUseActive()Z"))
    private boolean changeFlag1(boolean value, ServerPlayer player, Level level, @Local(ordinal = 0) boolean flag, @Local(ordinal = 0) BlockPos blockpos) {
        return player.isSecondaryUseActive() && flag && (!player.getMainHandItem().doesSneakBypassUse(level, blockpos, player));
    }
}
