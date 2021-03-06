/**
 * Copyright (c) 2016, Stupid Bird and/or its affiliates. All rights reserved.
 * STUPID BIRD PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @Project : YiDa
 * @Package : com.easarrive.aws.client.cloudsearch
 * @author <a href="http://www.lizhaoweb.net">李召(John.Lee)</a>
 * @Time : 18:05
 */
package com.easarrive.aws.client.cloudsearch;

import com.easarrive.aws.client.cloudsearch.model.Facet;
import com.easarrive.aws.client.cloudsearch.model.Highlight;
import com.easarrive.aws.client.cloudsearch.model.QueryParser;
import com.easarrive.aws.client.cloudsearch.model.SortOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="http://www.lizhaoweb.cn">李召(John.Lee)</a>
 * @notes Created on 2016年07月13日<br>
 * Revision of last commit:$Revision$<br>
 * Author of last commit:$Author$<br>
 * Date of last commit:$Date$<br>
 *
 */
@Data
public abstract class AmazonAbstractCloudSearchQuery {

    protected static final String CHARSET_UTF8 = "UTF-8";

    /**
     * Retrieves a cursor value you can use to page through large result sets. Use the size parameter to control the number of hits you want to include in each response. You can specify either the cursor or start parameter in a request, they are mutually exclusive. For more information, see Paginating Results.
     *
     * To get the first cursor, specify cursor=initial in your initial request. In subsequent requests, specify the cursor value returned in the hits section of the response.
     *
     * For example, the following request sets the cursor value to initial and the size parameter to 100 to get the first set of hits. The cursor for the next set of hits is included in the response.
     *
     * <pre>
     * search?q=john&cursor=initial&size=100&return=_no_fields
     * {
     * "status": {
     * "rid": "+/Xu5s0oHwojC6o=",
     * "time-ms": 15
     * },
     * "hits": {
     * "found": 503,
     * "start": 0,
     * cursor": "VegKzpYYQW9JSVFFRU1UeWwwZERBd09EUTNPRGM9ZA",
     * "hit": [
     * {"id": "tt0120601"},
     * {"id": "tt1801552"},
     * ...
     * ]
     * }
     * }
     * </pre>
     * To get the next set of hits, you specify the cursor value and the number of hits to retrieve.
     *
     * search?q=john&cursor=VegKzpYYQW9JSVFFRU1UeWwwZERBd09EUTNPRGM9ZA&size=100
     *
     * Required: No
     */
    private String cursor;

    /**
     * Defines an expression that can be used to sort results or specify search or filter criteria. You can also specify an expression as a return field. For more information about defining and using expressions, see Configuring Expressions.
     *
     * You can define and use multiple expressions in a search request. For example, the following request creates two expressions that are used to sort the results and includes them in the search results:
     *
     * search?q=(and (term field=genres 'Sci-Fi')(term field=genres 'Comedy'))&q.parser=structured
     * &expr.expression1=_score*rating
     * &expr.expression2=(1/rank)*year
     * &sort=expression1 desc,expression2 desc
     * &return=title,rating,rank,year,_score,expression1,expression2
     *
     * Required: No
     */
    private Map<String, String> expressions = new HashMap<String, String>();

    /**
     * Specifies a field that you want to get facet information for—FIELD is the name of the field. The specified field must be facet enabled in the domain configuration. Facet options are specified as a JSON object. If the JSON object is empty, facet.FIELD={}, facet counts are computed for all field values, the facets are sorted by facet count, and the top 10 facets are returned in the results.
     *
     * You can specify three options in the JSON object:
     *
     * sort specifies how you want to sort the facets in the results: bucket or count. Specify bucket to sort alphabetically or numerically by facet value (in ascending order). Specify count to sort by the facet counts computed for each facet value (in descending order). To retrieve facet counts for particular values or ranges of values, use the buckets option instead of sort.
     *
     * buckets specifies an array of the facet values or ranges you want to count. Buckets are returned in the order they are specified in the request. To specify a range of values, use a comma (,) to separate the upper and lower bounds and enclose the range using brackets or braces. A square brace, [ or ], indicates that the bound is included in the range, a curly brace, { or }, excludes the bound. You can omit the upper or lower bound to specify an open-ended range. When omitting a bound, you must use a curly brace. The sort and size options are not valid if you specify buckets.
     *
     * size specifies the maximum number of facets to include in the results. By default, Amazon CloudSearch returns counts for the top 10. The size parameter is only valid when you specify the sort option; it cannot be used in conjunction with buckets.
     *
     * For example, the following request gets facet counts for the year field, sorts the facet counts by value and returns counts for the top three:
     *
     * facet.year={sort:"bucket", size:3}
     * To specify which values or range of values you want to calculate facet counts for, use the buckets option. For example, the following request calculates and returns the facet counts by decade:
     *
     * facet.year={buckets:["[1970,1979]","[1980,1989]",
     * "[1990,1999]","[2000,2009]",
     * "[2010,}"]}
     * You can also specify individual values as buckets:
     *
     * facet.genres={buckets:["Action","Adventure","Sci-Fi"]}
     * Note that the facet values are case-sensitive—with the sample IMDB movie data, if you specify ["action","adventure","sci-fi"] instead of ["Action","Adventure","Sci-Fi"], all facet counts are zero.
     *
     * Required: No
     */
    private List<Facet> facets = new ArrayList<Facet>();

    /**
     * Specifies a structured query that filters the results of a search without affecting how the results are scored and sorted. You use fq in conjunction with the q parameter to filter the documents that match the constraints specified in the q parameter. Specifying a filter just controls which matching documents are included in the results, it has no effect on how they are scored and sorted. The fq parameter supports the full structured query syntax. For more information about using filters, see Filtering Matching Documents. For more information about structured queries, see Structured Search Syntax.
     *
     * Required: No
     */
    private String structuredQuery;

    /**
     * Retrieves highlights for matches in the specified text or text-array field. Highlight options are specified as a JSON object. If the JSON object is empty, the returned field text is treated as HTML and the first match is highlighted with emphasis tags: <em>search-term</em>.
     *
     * You can specify four options in the JSON object:
     *
     * format—specifies the format of the data in the text field: text or html. When data is returned as HTML, all non-alphanumeric characters are encoded. The default is html.
     *
     * max_phrases—specifies the maximum number of occurrences of the search term(s) you want to highlight. By default, the first occurrence is highlighted.
     *
     * pre_tag—specifies the string to prepend to an occurrence of a search term. The default for HTML highlights is <em>. The default for text highlights is *.
     *
     * post_tag—specifies the string to append to an occurrence of a search term. The default for HTML highlights is </em>. The default for text highlights is *.
     *
     * Examples: highlight.plot={}, highlight.plot={format:'text',max_phrases:2,pre_tag:'<b>',post_tag:'</b>'}
     *
     * Required: No
     */
    private List<Highlight> highlights = new ArrayList<Highlight>();

    /**
     * Controls whether partial results are returned if one or more index partitions are unavailable. When your search index is partitioned across multiple search instances, by default Amazon CloudSearch only returns results if every partition can be queried. This means that the failure of a single search instance can result in 5xx (internal server) errors. When you specify partial=true. Amazon CloudSearch returns whatever results are available and includes the percentage of documents searched in the search results (percent-searched). This enables you to more gracefully degrade your users' search experience. For example, rather than displaying no results, you could display the partial results and a message indicating that the results might be incomplete due to a temporary system outage.
     *
     * Default: False
     *
     * Required: No
     */
    private Boolean partial;

    /**
     * Formats JSON output so it's easier to read.
     *
     * Default: False
     *
     * Required: No
     */
    private Boolean pretty;

    /**
     * The search criteria for the request. How you specify the search criteria depends on the query parser used for the request and the parser options specified in the q.options parameter. By default, the simple query parser is used to process requests. To use the structured, lucene, or dismax query parser, you must also specify the q.parser parameter. For more information about specifying search criteria, see Searching Your Data with Amazon CloudSearch.
     *
     * Required: Yes
     */
    private String query;

    /**
     * Configure options for the query parser specified in the q.parser parameter.	The options are specified as a JSON object, for example: q.options={defaultOperator: 'or', fields: ['title^5','description']}.
     *
     * The options you can configure vary according to which parser you use:
     *
     * defaultOperator—The default operator used to combine individual terms in the search string. For example: defaultOperator: 'or'. For the dismax parser, you specify a percentage that represents the percentage of terms in the search string (rounded down) that must match, rather than a default operator. A value of 0% is the equivalent to OR, and a value of 100% is equivalent to AND. The percentage must be specified as a value in the range 0-100 followed by the percent (%) symbol. For example, defaultOperator: 50%. Valid values: and, or, a percentage in the range 0%-100% (dismax). Default: and (simple, structured, lucene) or 100 (dismax). Valid for: simple, structured, lucene, and dismax.
     *
     * fields—An array of the fields to search when no fields are specified in a search. If no fields are specified in a search and this option is not specified, all text and text-array fields are searched. You can specify a weight for each field to control the relative importance of each field when Amazon CloudSearch calculates relevance scores. To specify a field weight, append a caret (^) symbol and the weight to the field name. For example, to boost the importance of the title field over the description field you could specify: fields: ['title^5','description']. Valid values: The name of any configured field and an optional numeric value greater than zero. Default: All text and text-array fields. Valid for: simple, structured, lucene, and dismax.
     *
     * operators—An array of the operators or special characters you want to disable for the simple query parser. If you disable the and, or, or not operators, the corresponding operators (+, |, -) have no special meaning and are dropped from the search string. Similarly, disabling prefix disables the wildcard operator (*) and disabling phrase disables the ability to search for phrases by enclosing phrases in double quotes. Disabling precedence disables the ability to control order of precedence using parentheses. Disabling near disables the ability to use the ~ operator to perform a sloppy phrase search. Disabling the fuzzy operator disables the ability to use the ~ operator to perform a fuzzy search. escape disables the ability to use a backslash (\) to escape special characters within the search string. Disabling whitespace is an advanced option that prevents the parser from tokenizing on whitespace, which can be useful for Vietnamese. (It prevents Vietnamese words from being split incorrectly.) For example, you could disable all operators other than the phrase operator to support just simple term and phrase queries: operators:['and', 'not', 'or', 'prefix']. Valid values: and, escape, fuzzy, near, not, or, phrase, precedence, prefix, whitespace. Default: All operators and special characters are enabled. Valid for: simple.
     *
     * phraseFields—An array of the text or text-array fields you want to use for phrase searches. When the terms in the search string appear in close proximity within a field, the field scores higher. You can specify a weight for each field to boost that score. The phraseSlop option controls how much the matches can deviate from the search string and still be boosted. To specify a field weight, append a caret (^) symbol and the weight to the field name. For example, to boost phrase matches in the title field over the abstract field, you could specify: phraseFields:['title^3', 'abstract'] Valid values: The name of any text or text-array field and an optional numeric value greater than zero. Default: No fields. If you don't specify any fields with phraseFields, proximity scoring is disabled even if phraseSlop is specified. Valid for: dismax.
     *
     * phraseSlop—An integer value that specifies how much matches can deviate from the search phrase and still be boosted according to the weights specified in the phraseFields option. For example, phraseSlop: 2. You must also specify phraseFields to enable proximity scoring. Valid values: positive integers. Default: 0. Valid for: dismax.
     *
     * explicitPhraseSlop—An integer value that specifies how much a match can deviate from the search phrase when the phrase is enclosed in double quotes in the search string. (Phrases that exceed this proximity distance are not considered a match.) explicitPhraseSlop: 5. Valid values: positive integers. Default: 0. Valid for: dismax.
     *
     * tieBreaker—When a term in the search string is found in a document's field, a score is calculated for that field based on how common the word is in that field compared to other documents. If the term occurs in multiple fields within a document, by default only the highest scoring field contributes to the document's overall score. You can specify a tieBreaker value to enable the matches in lower-scoring fields to contribute to the document's score. That way, if two documents have the same max field score for a particular term, the score for the document that has matches in more fields will be higher. The formula for calculating the score with a tieBreaker is:
     *
     * (max field score) + (tieBreaker) * (sum of the scores for the rest of the matching fields)
     * For example, the following query searches for the term dog in the title, description, and review fields and sets tieBreaker to 0.1:
     *
     * q=dog&q.parser=dismax&q.options={fields:['title', 'description', 'review'], tieBreaker: 0.1}
     * If dog occurs in all three fields of a document and the scores for each field are title=1, description=3, and review=1, the overall score for the term dog is:
     *
     * 3 +  0.1 * (1+1) = 3.2
     * Set tieBreaker to 0 to disregard all but the highest scoring field (pure max). Set to 1 to sum the scores from all fields (pure sum). Valid values: 0.0 to 1.0. Default: 0.0. Valid for: dismax.
     *
     * Type: JSON object
     *
     * Default: See individual option descriptions.
     *
     * Required: No
     */
    private Map<String, String> queryOptions = new HashMap<String, String>();


    /**
     * Specifies which query parser to use to process the request: simple, structured, lucene, and dismax. If q.parser is not specified, Amazon CloudSearch uses the simple query parser.
     *
     * simple—perform simple searches of text and text-array fields. By default, the simple query parser searches all text and text-array fields. You can specify which fields to search by with the q.options parameter. If you prefix a search term with a plus sign (+) documents must contain the term to be considered a match. (This is the default, unless you configure the default operator with the q.options parameter.) You can use the - (NOT), | (OR), and * (wildcard) operators to exclude particular terms, find results that match any of the specified terms, or search for a prefix. To search for a phrase rather than individual terms, enclose the phrase in double quotes. For more information, see Searching Your Data with Amazon CloudSearch.
     *
     * structured—perform advanced searches by combining multiple expressions to define the search criteria. You can also search within particular fields, search for values and ranges of values, and use advanced options such as term boosting, matchall, and near. For more information, see Constructing Compound Queries.
     *
     * lucene—search using the Apache Lucene query parser syntax. For more information, see Apache Lucene Query Parser Syntax.
     *
     * dismax—search using the simplified subset of the Apache Lucene query parser syntax defined by the DisMax query parser. For more information, see DisMax Query Parser Syntax.
     *
     * Type: String
     *
     * Default: simple
     *
     * Required: No
     */
    private QueryParser queryParser;


    /**
     * The field and expression values to include in the response, specified as a comma-separated list. By default, a search response includes all return enabled fields (return=_all_fields). To return only the document IDs for the matching documents, specify return=_no_fields. To retrieve the relevance score calculated for each document, specify return=_score. You specify multiple return fields as a comma separated list. For example, return=title,_score returns just the title and relevance score of each matching document.
     *
     * Type: String
     *
     * Required: No
     */
    private String returnFields;


    /**
     * The maximum number of search hits to return.
     *
     * Default: 10
     * Required: No
     */
    private Integer size;

    /**
     * A comma-separated list of fields or custom expressions to use to sort the search results. You must specify the sort direction (asc or desc) for each field. For example, sort=year desc,title asc. You can specify a maximum of 10 fields and expressions. To use a field to sort results, it must be sort enabled in the domain configuration. Array type fields cannot be used for sorting. If no sort parameter is specified, results are sorted by their default relevance scores in descending order: sort=_score desc. You can also sort by document ID (sort=_id) and version (sort=_version).
     *
     * Type: String
     *
     * Required: No
     */
    private Map<String, SortOrder> sort = new HashMap<String, SortOrder>();

    /**
     * The offset of the first search hit you want to return. You can specify either the start or cursor parameter in a request, they are mutually exclusive. For more information, see Paginating Results.
     *
     * Default: 0 (the first hit)
     * Required: No
     */
    private Integer start;
}
