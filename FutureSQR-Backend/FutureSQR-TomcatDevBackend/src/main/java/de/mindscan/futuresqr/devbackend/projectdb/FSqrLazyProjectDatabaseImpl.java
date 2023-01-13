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
package de.mindscan.futuresqr.devbackend.projectdb;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
 */
public class FSqrLazyProjectDatabaseImpl {

    private Gson gson = new Gson();

    private HashMap<String, FSqrLazyProjectDBEntry> projectConfigurationMap = new HashMap<>();

    private Type projectConfigurationMapType = new TypeToken<HashMap<String, FSqrLazyProjectDBEntry>>() {
    }.getType();

    /**
     * 
     */
    public FSqrLazyProjectDatabaseImpl() {
        this.loadProjectDatabaseFromResource();
    }

    private void loadProjectDatabaseFromResource() {
        // actually we should use the class loader to access and deal with this resource
        Path userdbPath = Paths.get( "src/main/resources/projectdb/projectdatabase.json" );

        try (FileReader fileReader = new FileReader( userdbPath.toAbsolutePath().toString() )) {
            this.projectConfigurationMap = gson.fromJson( fileReader, projectConfigurationMapType );
        }
        catch (IOException e) {
            System.err.println( "could not find project database..." );
            e.printStackTrace();

            ClassLoader cl = this.getClass().getClassLoader();
            System.err.println( cl.getResource( "projectdb/projectdatabase.json" ) );
            try (InputStream is = cl.getResourceAsStream( "userdb/userdatabase.json" ); Reader isr = new InputStreamReader( is )) {
                this.projectConfigurationMap = gson.fromJson( isr, projectConfigurationMapType );
            }
            catch (Exception ex) {
                System.err.println( "yould not access alternate project database" );
                ex.printStackTrace();
            }
        }
    }

    public FSqrLazyProjectDBEntry getProjectConfiguration( String projectId ) {
        return this.projectConfigurationMap.get( projectId );
    }

    public Collection<FSqrLazyProjectDBEntry> getAllProjects() {
        return projectConfigurationMap.values();
    }

}