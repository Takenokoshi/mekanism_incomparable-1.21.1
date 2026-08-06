package com.takenokoshi.mekin.blockentity.interfaces;

import com.takenokoshi.mekut.blockentity.interfaces.IHasInputChemicalTank;
import com.takenokoshi.mekut.blockentity.interfaces.IHasMachineEnergyContainer;
import com.takenokoshi.mekut.blockentity.interfaces.IScaledProgressProvider;

public interface IChemicalCrystallizer extends IHasMachineEnergyContainer, IHasInputChemicalTank,IScaledProgressProvider {
    int getEnergySlotX();
}
