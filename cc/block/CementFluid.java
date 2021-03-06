package cc.block;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CementFluid extends Fluid
{
    public CementFluid()
    {
        super("ConcreteFluid");
        setDensity(1);
        setViscosity(3500);
        setGaseous(true);
        FluidRegistry.registerFluid(this);
    }
}