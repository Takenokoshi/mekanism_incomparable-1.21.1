package com.takenokoshi.mekin.blockentity.interfaces;

import com.takenokoshi.mekaddonlib.blockentity.interfaces.IWarningSupporter;
import com.takenokoshi.mekut.blockentity.interfaces.IRecipeViewerTypeProvider;

import mekanism.api.chemical.IChemicalTank;

public interface ISimpleItemStackChemicalToChemicalMachine extends IWarningSupporter, IRecipeViewerTypeProvider {
    IChemicalTank getInputTank();

    IChemicalTank getOutputTank();

    double getScaledProgress();
}
