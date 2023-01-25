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
import de.mindscan.futuresqr.domain.databases.FSqrScmProjectConfigurationRepositoryImpl;
import de.mindscan.futuresqr.domain.databases.FSqrScmProjectRevisionRepositoryImpl;
import de.mindscan.futuresqr.domain.databases.FSqrScmUserRepositoryImpl;

/**
 * 
 */
public class FSqrApplicationServicesImpl implements FSqrApplicationServices {

    private FSqrScmProjectConfigurationRepositoryImpl configurationRepository;
    private FSqrScmProjectRevisionRepositoryImpl revisionRepository;
    private FSqrScmUserRepositoryImpl userRepository;
    private FSqrCodeReviewRepositoryImpl reviewRepository;
    private FSqrSystemInstanceConfigurationImpl systemConfiguration;

    /**
     * 
     */
    public FSqrApplicationServicesImpl() {
        this.systemConfiguration = new FSqrSystemInstanceConfigurationImpl();
        this.configurationRepository = new FSqrScmProjectConfigurationRepositoryImpl();
        this.revisionRepository = new FSqrScmProjectRevisionRepositoryImpl();
        this.userRepository = new FSqrScmUserRepositoryImpl();
        this.reviewRepository = new FSqrCodeReviewRepositoryImpl();

        // we need to boot the instance
        // and then we actually need a way to provide some data from externalproviders, and then
        // finalize the boot of this application somehow.
        initializeServiceInstances( this );

        // we don't want to deal with the internals of this Review system from the outside e.g. the Web servers
    }

    void initializeServiceInstances( FSqrApplicationServices services ) {
        this.systemConfiguration.setApplicationServices( services );
        // TODO: implement the initializer for the configuration repository.
        // this.configurationRepository.set
        this.revisionRepository.setApplicationServices( services );
        this.userRepository.setApplicationServices( services );
        this.reviewRepository.setApplicationServices( services );
    }

    // TODO we want to provide some dataprovider ability, the application can ask, e.g. on restart or on demand, 
    //      when the data is not yet in memory (e.g. not cached)
    // TODO when we provide a data provider, and a data provider is not set, it will invoke the initial/essential data

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmProjectConfigurationRepositoryImpl getConfigurationRepository() {
        return this.configurationRepository;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmProjectRevisionRepositoryImpl getRevisionRepository() {
        return this.revisionRepository;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrScmUserRepositoryImpl getUserRepository() {
        return this.userRepository;
    }

    /**
     * @return the reviewRepository
     */
    @Override
    public FSqrCodeReviewRepositoryImpl getReviewRepository() {
        return reviewRepository;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public FSqrSystemInstanceConfigurationImpl getSystemConfiguration() {
        return systemConfiguration;
    }
}