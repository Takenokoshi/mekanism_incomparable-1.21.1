package com.takenokoshi.mekin.recipe.type;

import com.moakiee.ae2lt.lightning.LightningTransformRecipe;
import com.moakiee.ae2lt.lightning.LightningTransformRecipeInput;
import com.takenokoshi.mekin.recipe.inputcache.LightningTransformInputRecipeCache;
import com.takenokoshi.mekut.recipe.type.WrappedRecipeType;
import static com.moakiee.ae2lt.registry.ModRecipeTypes.LIGHTNING_TRANSFORM_TYPE;

public class MekInWrappedRecipeTypes {

    public static final WrappedRecipeType<LightningTransformRecipeInput, LightningTransformRecipe, LightningTransformInputRecipeCache> LIGHTNING_TRANSFORM = new WrappedRecipeType<>(
            LIGHTNING_TRANSFORM_TYPE.getId(),
            LightningTransformInputRecipeCache::new,
            LIGHTNING_TRANSFORM_TYPE.get());

}
