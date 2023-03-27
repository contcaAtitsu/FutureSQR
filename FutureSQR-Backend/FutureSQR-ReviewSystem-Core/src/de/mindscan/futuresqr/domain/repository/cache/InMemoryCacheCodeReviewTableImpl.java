/**
 * 
 * MIT License
 *
 * Copyright (c) 2023 Maxim Gansert, Mindscan
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * 
 */
package de.mindscan.futuresqr.domain.repository.cache;

import java.util.HashMap;
import java.util.Map;

import de.mindscan.futuresqr.domain.model.FSqrCodeReview;

/**
 * 
 */
public class InMemoryCacheCodeReviewTableImpl {

    // also LRU cache implementation, such that the number of reviews in memory
    // are limited, and those which aren't used are rotated out from cache.

    // TODO: replace this multimap (map of maps) with single map and a simple key calculation.
    // TODO: project id and review id should be UUIDs?
    private Map<String, Map<String, FSqrCodeReview>> projectIdReviewIdToCodeReviewCache;

    /**
     * 
     */
    public InMemoryCacheCodeReviewTableImpl() {
        this.projectIdReviewIdToCodeReviewCache = new HashMap<>();
    }

    public boolean isCached( String projectId, String reviewId ) {
        if (projectIdReviewIdToCodeReviewCache.containsKey( projectId )) {
            return projectIdReviewIdToCodeReviewCache.get( projectId ).containsKey( reviewId );
        }
        return false;
    }

    public FSqrCodeReview getCodeReview( String projectId, String reviewId ) {
        if (isCached( projectId, reviewId )) {
            return projectIdReviewIdToCodeReviewCache.get( projectId ).get( reviewId );
        }
        return null;
    }

    // TODO: getOrCompute which will either get or execute the expression and then put the code review into the table.
    public FSqrCodeReview getCodeReviewOrComputeIfAbsent( String projectId, String reviewId ) {
        return null;
    }

    public void putCodeReview( String projectId, String reviewId, FSqrCodeReview coreReview ) {

    }
}
