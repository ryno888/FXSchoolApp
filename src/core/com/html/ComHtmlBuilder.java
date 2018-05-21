/*
 * Class 
 * @filename ComHtmlBuilder 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 24 Nov 2017 * 
 */
package core.com.html;

import core.com.utils.ComHashmap;
import j2html.TagCreator;
import j2html.tags.ContainerTag;
import java.util.HashMap;
import jdk.nashorn.internal.runtime.JSType;

/**
 *
 * @author Ryno
 */
public class ComHtmlBuilder {
    StringBuilder html = new StringBuilder();
    //--------------------------------------------------------------------------
    public ComHtmlBuilder label(String label, HashMap options){
        
        options = ComHashmap.merge(new HashMap(){{
        }}, options);
        
		add_html(TagCreator.label(label), options);
        return this;
    }
    //--------------------------------------------------------------------------
    public ComHtmlBuilder img(String src, HashMap options){
        options = ComHashmap.merge(new HashMap(){{
        }}, options);
        
		add_html(TagCreator.img().withSrc(src), options);
        return this;
    }
    //--------------------------------------------------------------------------
    public ComHtmlBuilder header(int type, String title, HashMap options){
        options = ComHashmap.merge(new HashMap(){{
        }}, options);
        
		switch(type){
			case 1: add_html(TagCreator.h1(title), options); break;
			case 2: add_html(TagCreator.h2(title), options); break;
			case 3: add_html(TagCreator.h3(title), options); break;
		}
        return this;
    }
    //--------------------------------------------------------------------------
    private ContainerTag apply_options(Object element, HashMap options){
		String css = this.get_css(options);
        String attr = this.get_attributes(options);
		ContainerTag el =  (ContainerTag) element;
		if(css != null){ el.withClass(css); }
		if(attr != null){ el.attr(attr); }
        return el;
    }
    //--------------------------------------------------------------------------
    public ComHtmlBuilder add_html(Object html){ return this.add_html(html, null); }
    //--------------------------------------------------------------------------
    public ComHtmlBuilder add_html(Object html, HashMap options){
		options = ComHashmap.merge(new HashMap(){{
        }}, options);
		
		if("j2html.tags.ContainerTag".equals(html.getClass().getName())){
			ContainerTag el =  (ContainerTag) html;
			apply_options(el, options);
			this.html.append(el.render()).append("\n");
		}else if(JSType.isString(html)){
			this.html.append(html).append("\n");
		}
        return this;
    }
    //--------------------------------------------------------------------------
    private String get_css(HashMap <String, Object> options){
        StringBuilder css = new StringBuilder();
        options.forEach((key, value) -> {
            if(key.codePointAt(0) == '.' && value.equals(true)){
                if(!css.toString().isEmpty()){ css.append(" "); }
                css.append(key.substring(1));
            }
        });
        return css.toString().isEmpty() ? null : css.toString();
    }
    //--------------------------------------------------------------------------
    private String get_attributes(HashMap <String, Object> options){
        StringBuilder attr = new StringBuilder();
        options.forEach((key, value) -> {
            if(key.codePointAt(0) == '@'){
                if(!attr.toString().isEmpty()){ attr.append(" "); }
                attr.append(key.substring(1));
                attr.append("=");
                attr.append("'").append(value.toString()).append("'");
            }
        });
        return attr.toString().isEmpty() ? null : attr.toString();
    }
    //--------------------------------------------------------------------------
    public String get_html(){
        return this.html.toString();
    }
    //--------------------------------------------------------------------------
}


//        ComHtmlBuilder builder = new ComHtmlBuilder();
//        builder.label("Ryno", new HashMap<String, Object>(){{
//            this.put(".test", true);
//            this.put("@test1", "test1");
//        }});
//        builder.header(1,"Ryno", new HashMap<String, Object>(){{
//			this.put("@111", "111");
//        }});
//        System.out.println(builder.get_html());