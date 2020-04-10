package net.tluw.sperling;

import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;


public class PluginMetadata implements MetadataValue {

	private Plugin metaPlugin;
	private String metaValue;
	
	public PluginMetadata(Plugin metaPlugin, String metaValue) {
		this.metaPlugin = metaPlugin;
		this.metaValue = metaValue;
	}
	
	public boolean equals(String stringValue) {
		return metaValue.equals(stringValue);
	}
	
	public void set(String stringValue) {
		this.metaValue = stringValue;
	}
	
	@Override
	public boolean asBoolean() {
		throw new NullPointerException();
	}
	 
	@Override
	public byte asByte() {
		throw new NullPointerException();
	}
	 
	@Override
	public double asDouble() {
		return Double.parseDouble(metaValue);
	}
	 
	@Override
	public float asFloat() {
		throw new NullPointerException();
	}
	 
	@Override
	public int asInt() {
		throw new NullPointerException();
	}
	 
	@Override
	public long asLong() {
		throw new NullPointerException();
	}
	 
	@Override
	public short asShort() {
		throw new NullPointerException();
	}
	 
	@Override
	public String asString() {
		return metaValue;
	}
	 
	@Override
	public Plugin getOwningPlugin() {
		return metaPlugin;
	}
	 
	@Override
	public void invalidate() {
		return;
	}
	 
	@Override
	public String value() {
		return metaValue;
	}
	
	
	
}
