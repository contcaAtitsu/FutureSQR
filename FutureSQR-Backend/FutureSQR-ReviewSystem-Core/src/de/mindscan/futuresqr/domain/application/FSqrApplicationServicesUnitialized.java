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
package de.mindscan.futuresqr.domain.application;

import de.mindscan.futuresqr.domain.configuration.FSqrSystemInstanceConfigurationImpl;
import de.mindscan.futuresqr.domain.databases.FSqrCodeReviewRepositoryImpl;
import de.mindscan.futuresqr.domain.databases.FSqrDiscussionThreadRepositoryImpl;
import de.mindscan.futuresqr.domain.databases.FSqrScmProjectRevisionRepositoryImpl;
import de.mindscan.futuresqr.domain.repository.FSqrScmProjectConfigurationRepository;
import de.mindscan.futuresqr.domain.repository.FSqrUserToProjectRepository;
import de.mindscan.futuresqr.domain.repository.impl.FSqrScmUserRepositoryImpl;

/**
 * 
 */
public class FSqrApplicationServicesUnitialized implements FSqrApplicationServices {

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmProjectConfigurationRepository getConfigurationRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmProjectRevisionRepositoryImpl getRevisionRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmUserRepositoryImpl getUserRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrCodeReviewRepositoryImpl getReviewRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrSystemInstanceConfigurationImpl getSystemConfiguration() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrUserToProjectRepository getUserToProjectRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrDiscussionThreadRepositoryImpl getDiscussionThreadRepository() {
        throw new RuntimeException( "Please initialize Application Services first before using this method. See Stack." );
    }
}
