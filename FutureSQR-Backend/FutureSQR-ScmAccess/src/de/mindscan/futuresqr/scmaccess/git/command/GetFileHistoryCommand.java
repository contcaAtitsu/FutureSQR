/**
 * 
 * MIT License
 *
 * Copyright (c) 2022 Maxim Gansert, Mindscan
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
package de.mindscan.futuresqr.scmaccess.git.command;

import java.util.ArrayList;
import java.util.List;

import de.mindscan.futuresqr.scmaccess.git.GitCLICommonConstants;
import de.mindscan.futuresqr.scmaccess.git.GitCommand;

/**
 * Implementation of "getParticularFileHistory"
 */
public class GetFileHistoryCommand extends GitCommand implements GitCommandWithFilePath {

    private String filePath;

    /**
     * 
     */
    public GetFileHistoryCommand( String filePath ) {
        this.filePath = filePath;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public List<String> getArguments() {
        List<String> args = new ArrayList<String>();

        args.add( GitCLICommonConstants.GIT_COMMAND_LOG );
        args.add( GitCLICommonConstants.FOLLOW );
        args.add( GitCLICommonConstants.GIT_PRETTY_FORMAT_WITH_PARAMETERS );
        args.add( GitCLICommonConstants.MINUSMINUS );
        args.add( this.filePath );

        return args;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public boolean isCacheable() {
        // Actually this result can be used as long as the head did not change for this file.
        return false;
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public String getFilePath() {
        return filePath;
    }

}
