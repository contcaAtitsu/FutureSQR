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
package de.mindscan.futuresqr.scmaccess.git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.mindscan.futuresqr.scmaccess.ScmContentProvider;
import de.mindscan.futuresqr.scmaccess.types.ScmFileContent;
import de.mindscan.futuresqr.scmaccess.types.ScmPath;
import de.mindscan.futuresqr.scmaccess.types.ScmRepository;

/**
 * 
 */
public class GitScmContentProvider implements ScmContentProvider {

    // Okay let's say we have a local repository for git
    // lets say we have a configuration for

    // start with hard coded git executable to make things work.
    public static final String GIT_EXECUTABLE_PATH = "C:\\Program Files\\Git\\cmd\\git.exe";

    private void executeGitCommand( String[] parameters ) {
        List<String> gitCommand = new ArrayList<String>();
        gitCommand.add( GIT_EXECUTABLE_PATH );
        gitCommand.addAll( Arrays.asList( parameters ) );

        try {
            Process process = new ProcessBuilder( gitCommand ).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader( is );
            BufferedReader br = new BufferedReader( isr );

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println( line );
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public ScmFileContent getFileContentForRevision( ScmRepository repository, String revisionId, ScmPath filePath ) {
        String[] parameters = { "-C", "D:\\Temp\\future-square-cache\\FutureSQR", "--no-pager", "show", revisionId + ":" + filePath.getPath() };

        executeGitCommand( parameters );

        return null;
    }

}
