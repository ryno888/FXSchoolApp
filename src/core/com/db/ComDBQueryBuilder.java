/*
 * Class 
 * @filename DBDBQueryBuilder 
 * @encoding UTF-8
 * @author Liquid Edge Solutions  * 
 * @copyright Copyright Liquid Edge Solutions. All rights reserved. * 
 * @programmer Ryno van Zyl * 
 * @date 25 Aug 2017 * 
 */
package core.com.db;

import core.com.array.ComArray;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Ryno
 */
public class ComDBQueryBuilder {
    //--------------------------------------------------------------------------
    // properties
    //--------------------------------------------------------------------------
    private final ArrayList select = new ArrayList(); 
    private final HashMap<String, ArrayList> where = new HashMap<>(); 
    private final HashMap<String, String> parts = new HashMap<>(); 
    
    //--------------------------------------------------------------------------
    // enum
    //--------------------------------------------------------------------------
    public ComDBQueryBuilder () {
       this.where.put("AND", new ArrayList());
       this.where.put("OR", new ArrayList());
       
       this.parts.put("select", "");
       this.parts.put("from", "");
       this.parts.put("where", "");
       this.parts.put("groupby", "");
       this.parts.put("orderby", "");
       this.parts.put("limit", "");
       this.parts.put("having", "");
       this.parts.put("distinct", "");
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder select(String select) {
        if( select == null ) throw new IllegalArgumentException("select is null!");
        this.select.add(select);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder from(String from) {
        if( from == null ) throw new IllegalArgumentException("from is null!");
        this.parts.put("from", from);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder where(String operator, String where) {
        if( where == null ) throw new IllegalArgumentException("where is null!");
        
        ArrayList list = this.where.get(operator);
        list.add(where);
        this.where.put(operator, list);
        
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder distinct() {
        this.parts.put("distinct", " DISTINCT ");
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder groupBy(String groupBy) {
        if( groupBy == null ) throw new IllegalArgumentException("groupBy is null!");
        this.parts.put("groupby", groupBy);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder having(String having) {
        if( having == null ) throw new IllegalArgumentException("having is null!");
        this.parts.put("having", having);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder orderBy(String orderBy) {
        if( orderBy == null ) throw new IllegalArgumentException("orderBy is null!");
        this.parts.put("orderby", orderBy);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder limit(String limit) {
        if( limit == null ) throw new IllegalArgumentException("limit is null!");
        this.parts.put("limit", limit);
        return this;
    }
    //--------------------------------------------------------------------------
    public final ComDBQueryBuilder limit(int limit) {
        return this.limit(String.valueOf(limit));
    }
    //--------------------------------------------------------------------------
    private String buildSelect() {
        if(this.select.size() > 0){
            this.parts.put("select", ComArray.implode(",", this.select));
        }
        
        return this.parts.get("select");
    }
    //--------------------------------------------------------------------------
    private String buildWhere() {
        StringBuilder query = new StringBuilder();
        ArrayList innerWhere = new ArrayList();
        if(this.where.get("AND").size() > 0){
            innerWhere.add(ComArray.implode("AND ", this.where.get("AND")));
            
        }
        if(this.where.get("OR").size() > 0){
            innerWhere.add("( "+ComArray.implode(" OR ", this.where.get("OR"))+" )");
        }
        
        
        query.append(ComArray.implode(" AND ", innerWhere));
        if(!query.toString().isEmpty()){
            this.parts.put("where", query.toString());
        }
        
        return this.parts.get("where");
    }
    //--------------------------------------------------------------------------
    
    private String build() {
        StringBuilder query = new StringBuilder();
        if(this.select.size() > 0){ this.buildSelect(); }
        if(this.where.size() > 0){ this.buildWhere(); }
        
        
        if(!this.parts.get("select").isEmpty()){
            query.append("SELECT ");
            if(!this.parts.get("distinct").isEmpty()){
                query.append(this.parts.get("distinct"));
            }
            query.append(this.parts.get("select"));
        }
        if(!this.parts.get("from").isEmpty()){
            query.append(" FROM ").append(this.parts.get("from"));
        }
        if(!this.parts.get("where").isEmpty()){
            query.append(" WHERE ").append(this.parts.get("where"));
        }
        if(!this.parts.get("groupby").isEmpty()){
            query.append(" GROUP BY ").append(this.parts.get("groupby"));
        }
        if(!this.parts.get("orderby").isEmpty()){
            query.append(" ORDER BY ").append(this.parts.get("orderby"));
        }
        if(!this.parts.get("limit").isEmpty()){
            query.append(" LIMIT ").append(this.parts.get("limit"));
        }
        return query.toString();
    }
    //--------------------------------------------------------------------------
    public String get_sql() {
        return this.build();
    }
    //--------------------------------------------------------------------------
    
}
