package com.rodrigouchoa.tooltip.messages;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessages;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.html_basic.HtmlBasicRenderer;


/**
 * Renderer for the tooltipMessages tag.
 * 
 * @author Rodrigo Uchoa (rodrigo.uchoa@gmail.com)
 * @author Lucas Lopes (fbl.lucas@gmail.com)
 *
 */
@ResourceDependencies({
	@ResourceDependency(library = "tooltip-messages", name = "js/jquery-1.11.2.js"),
	@ResourceDependency(library = "tooltip-messages", name = "js/jquery.validationEngine-pt_BR.js"),
	@ResourceDependency(library = "tooltip-messages", name = "js/jquery.validationEngine.js"),
	@ResourceDependency(library = "tooltip-messages", name = "css/validationEngine.jquery.css")
})
public class TooltipMessagesRenderer extends HtmlBasicRenderer {
	
	/* This is where the magic happens.
	 * 
	 * As you can see the code is pretty simple. All this renderer do is write 
	 * the JQuery Validation Engine API calls inside a <script> element.
	 * 
	 * Beware that this code assumes that all input elements will have
	 * a JSF clientId written!
	 * 
	 * Check the JQuery Validation Engine docs at:
	 * https://github.com/posabsolute/jQuery-Validation-Engine
	 * 
	 * (non-Javadoc)
	 * @see com.sun.faces.renderkit.html_basic.HtmlBasicRenderer#encodeEnd(javax.faces.context.FacesContext, javax.faces.component.UIComponent)
	 */
	@Override
	public void encodeEnd(FacesContext facesContext, UIComponent component)
			throws IOException {
		rendererParamsNotNull(facesContext, component);

        if (!shouldEncode(component)) {
            return;
        }
        
        UIMessages uiMessages = (UIMessages) component;		
		ResponseWriter writer = facesContext.getResponseWriter();
		assert (writer != null);
		
		Iterator<String> clientIds = facesContext.getClientIdsWithMessages();
	
		writer.startElement("span", component);
	    writeIdAttributeIfNecessary(facesContext, writer, component);
	    writer.startElement("script", component);
		
		while (clientIds.hasNext()) {
			String clientId = clientIds.next();
			for (FacesMessage facesMessage : facesContext.getMessageList(clientId)) {
				if (facesMessage.isRendered() && !uiMessages.isRedisplay()) {
					continue;
				}		
				facesMessage.rendered();
				writer.write(String.format("jQuery(document.getElementById('%s')).validationEngine('showPrompt', '%s', 'error', 'topRight', true);\n",
								clientId, facesMessage.getDetail()));
			}
		}
		
		writer.endElement("script");
		writer.endElement("span");
	}

}
