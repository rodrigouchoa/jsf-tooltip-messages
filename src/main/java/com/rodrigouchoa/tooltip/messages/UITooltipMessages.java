package com.rodrigouchoa.tooltip.messages;

import javax.faces.component.UIMessages;

/**
 * 
 * @author Rodrigo Uchoa (rodrigo.uchoa@gmail.com)
 *
 */
public class UITooltipMessages extends UIMessages {
	
    public static final String COMPONENT_FAMILY = "com.rodrigouchoa.tooltip.messages";
    
    public static final String RENDERER_TYPE = "com.rodrigouchoa.tooltip.messages.TooltipMessagesRenderer";
	
	public UITooltipMessages() {
		setRendererType(RENDERER_TYPE);
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_FAMILY;
	}

}
