package me.xakeplusplus.spg.mixin;

import java.util.Map;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import me.xakeplusplus.spg.SpaghettiClient;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
 
public class MixinLoader implements IFMLLoadingPlugin {

	private static final Logger log = LogManager.getLogger("spaghetti-client");
 
	public MixinLoader(){
		MixinBootstrap.init();
		log.info("Mixin Loader Initialized.");
		Mixins.addConfiguration("mixins.spg.json");
	}
 
	@Override
	public String[] getASMTransformerClass(){
		return new String[0];
	}
 
	@Override
	public String getModContainerClass(){
		return null;
	}
 
	@Nullable
	@Override
	public String getSetupClass(){
		return null;
	}
 
	@Override
	public void injectData(Map<String, Object> data){
	}
 
	@Override
	public String getAccessTransformerClass(){
		return null;
	}
}