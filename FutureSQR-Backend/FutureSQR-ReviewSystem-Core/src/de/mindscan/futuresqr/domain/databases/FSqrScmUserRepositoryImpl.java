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
package de.mindscan.futuresqr.domain.databases;

import de.mindscan.futuresqr.domain.application.ApplicationServicesSetter;
import de.mindscan.futuresqr.domain.application.FSqrApplicationServices;
import de.mindscan.futuresqr.domain.application.FSqrApplicationServicesUnitialized;
import de.mindscan.futuresqr.domain.model.user.FSqrSystemUser;
import de.mindscan.futuresqr.domain.repository.FSqrScmUserRepository;
import de.mindscan.futuresqr.domain.repository.cache.InMemoryCacheAlternateScmAliasTableImpl;
import de.mindscan.futuresqr.domain.repository.cache.InMemoryCacheSystemUserTableImpl;

/**
 * TODO: rework the repository to use a database instead of the in-memory + scm data pull implementation
 */
public class FSqrScmUserRepositoryImpl implements FSqrScmUserRepository, ApplicationServicesSetter {

    private FSqrApplicationServices applicationServices;

    private InMemoryCacheAlternateScmAliasTableImpl aliasScmNameCache;

    private InMemoryCacheSystemUserTableImpl systemUserCache;

    /**
     * 
     */
    public FSqrScmUserRepositoryImpl() {
        this.applicationServices = new FSqrApplicationServicesUnitialized();
        this.systemUserCache = new InMemoryCacheSystemUserTableImpl();
        this.aliasScmNameCache = new InMemoryCacheAlternateScmAliasTableImpl();
    }

    @Override
    public void setApplicationServices( FSqrApplicationServices services ) {
        this.applicationServices = services;
    }

    @Override
    public String getUserUUID( String authorId ) {
        return aliasScmNameCache.getUserIdForScmAlias( authorId );
    }

    @Override
    public void addUserHandle( String authorHandle, String authorUUID ) {
        this.aliasScmNameCache.addScmAlias( authorHandle, authorUUID );
    }

    @Override
    public void addUserEntry( FSqrSystemUser user ) {
        this.systemUserCache.putSystemUser( user.getUserUUID(), user );
    }

    @Override
    public boolean isUserUUIDPresent( String uuid ) {
        return systemUserCache.isCached( uuid );
    }

    @Override
    public boolean isLogonNamePresent( String logonName ) {
        return this.systemUserCache.isLoginNamePresent( logonName );
    }

    @Override
    public FSqrSystemUser getUserByUUID( String uuid ) {
        if (isUserUUIDPresent( uuid )) {
            return this.systemUserCache.getSystemUser( uuid );
        }
        return null;
    }
}
