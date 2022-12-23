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
package de.mindscan.futuresqr.scmaccess.git.processor;

import de.mindscan.futuresqr.scmaccess.git.GitCLICommandOutput;
import de.mindscan.futuresqr.scmaccess.types.ScmFileContent;

/**
 * 
 */
public class ScmFileContentOutputProcessor {

    /**
     * 
     */
    public ScmFileContentOutputProcessor() {
        // TODO filename, fileRevision, repository, content
        // TODO: interessant w�re, diese informationen gewisserma�en vom GitCliCommandOutput zu bekommen, bspw. 
        //       wir das Gitcommand loggen lassen und das Repo und so und das alles wie zuf�llig im GitCLICommandOutput stehen w�rde.
    }

    public ScmFileContent parse( GitCLICommandOutput output ) {
        ScmFileContent scmFileContent = new ScmFileContent();
        // output.getRepository();
        // output.getCommand();

        return scmFileContent;
    }

}