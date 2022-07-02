package net.viktornr.aotmod.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.TrackedData;
import org.spongepowered.asm.mixin.Unique;

import javax.annotation.Nullable;

public interface ITransformData {

    boolean getBooleanData();
    void setBooleanData(boolean newValue);

    boolean getIsTransforming();
    void setIsTransforming(boolean newValue);

    void initTransformation();
    void playerEntityInit();
}
