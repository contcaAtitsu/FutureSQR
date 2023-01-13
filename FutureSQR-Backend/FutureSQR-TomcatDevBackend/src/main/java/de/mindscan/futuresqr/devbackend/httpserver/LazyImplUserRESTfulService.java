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
package de.mindscan.futuresqr.devbackend.httpserver;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import de.mindscan.futuresqr.devbackend.httpresponse.OutputCsrfTokenModel;
import de.mindscan.futuresqr.devbackend.httpresponse.OutputLoginDataModel;
import de.mindscan.futuresqr.devbackend.httpresponse.OutputStatusOkayModel;
import de.mindscan.futuresqr.devbackend.httpresponse.OutputUserProjectEntry;
import de.mindscan.futuresqr.devbackend.projectdb.FSqrLazyProjectDBEntry;
import de.mindscan.futuresqr.devbackend.projectdb.FSqrLazyProjectDatabaseImpl;
import de.mindscan.futuresqr.devbackend.userdb.FSqrLazyUserDBEntry;
import de.mindscan.futuresqr.devbackend.userdb.FSqrLazyUserDatabaseImpl;

/**
 * 
 */
@javax.ws.rs.Path( "/user" )
public class LazyImplUserRESTfulService {

    private static FSqrLazyUserDatabaseImpl userDB = new FSqrLazyUserDatabaseImpl();
    private static FSqrLazyProjectDatabaseImpl projectDB = new FSqrLazyProjectDatabaseImpl();

    @javax.ws.rs.Path( "/authenticate" )
    @POST
    @Produces( "application/json" )
    // @Consumes( MediaType.APPLICATION_FORM_URLENCODED )
    public String postLoginDataForAuthentication( //
                    String requestBody
//                    // EFF this shit - won't properly work value is null for some effing reason.....
//                    // EFF jakarta implementatuion - https://guntherrotsch.github.io/blog_2021/jaxrs-multipart-client.html
//                    @FormParam( "username" ) String username, //
//                    @FormParam( "password" ) String password 
    ) {

        // XXX: this is bad but i really don't want to deal with the multipart formdata right now.
        //      bad enough this sh*t is not solved once and for all in tomcat 7 - eff this.
        String username = "mindscan-de";
        String password = "test";

        // #1 check if user is present in the userdatabase
        if (!userDB.hasUser( username )) {
            // todo provide a 404 and a good response
            throw new RuntimeException( "No such user or not authenticated. " + " username:'" + username + "'; password:'" + password + "'" );
        }

        // #2 get user entry using the username
        FSqrLazyUserDBEntry userEntry = userDB.getUserEntryByLogonName( username );

        // #3 TODO: register the user as an authenticated user

        OutputLoginDataModel response = new OutputLoginDataModel();
        response.uuid = userEntry.uuid;
        response.loginname = userEntry.loginname;
        response.displayname = userEntry.displayname;
        response.avatarlocation = userEntry.avatarlocation;
        response.email = userEntry.email;

        // #4 figure out the roles and featureflags for this user
        // #5 if admin, add admin role to capabilities

        // #6 return
        if ("mindscan-de".equals( username )) {
            response.capabilities.roles.add( "admin" );
        }

        Gson gson = new Gson();
        return gson.toJson( response );
    }

    @javax.ws.rs.Path( "/reauthenticate" )
    @POST
    @Produces( "application/json" )
    public String postReauthenticationLoginData( //
//                    @FormParam( "assumedusername" )
    // String assumedUserName 
    ) {

        // XXX: this is bad but i really don't want to deal with the multipart formdata right now.
        //      bad enough this sh*t is not solved once and for all in tomcat 7 - eff this.
        String assumedUserName = "mindscan-de";

        // TODO: reimplement python #postReauthenticateLoginData

        // #1 session handling and session checking for this alleged account.
        if (isAuthSession( assumedUserName )) {

            // #2 get user entry using the username
            FSqrLazyUserDBEntry userEntry = userDB.getUserEntryByLogonName( assumedUserName );

            // #3 TODO: register the user as an authenticated user

            OutputLoginDataModel response = new OutputLoginDataModel();
            response.uuid = userEntry.uuid;
            response.loginname = userEntry.loginname;
            response.displayname = userEntry.displayname;
            response.avatarlocation = userEntry.avatarlocation;
            response.email = userEntry.email;

            // #4 figure out the roles and featureflags for this user
            // #5 if admin, add admin role to capabilities

            // #6 return
            if ("mindscan-de".equals( assumedUserName )) {
                response.capabilities.roles.add( "admin" );
            }

            Gson gson = new Gson();
            return gson.toJson( response );

        }
        OutputLoginDataModel response = new OutputLoginDataModel();

        Gson gson = new Gson();
        return gson.toJson( response );
    }

    private boolean isAuthSession( String assumedUserName ) {
        // TODO implement a real session handling
        return true;
    }

    @javax.ws.rs.Path( "/csrf" )
    @GET
    @Produces( "application/json" )
    public String getCrsfToken() {
        OutputCsrfTokenModel response = new OutputCsrfTokenModel();

        Gson gson = new Gson();
        return gson.toJson( response );
    }

    @javax.ws.rs.Path( "/logout" )
    @POST
    @Produces( "application/json" )
    public String postLogoutData( //
                    @FormParam( "username" ) String userName ) {
        // TODO: reimplement python #postLogoutData

        OutputStatusOkayModel response = new OutputStatusOkayModel();

        Gson gson = new Gson();
        return gson.toJson( response );
    }

    @javax.ws.rs.Path( "/starredprojects" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public String getUserStarredProjects( @QueryParam( "userid" ) String userUUID ) {
        Collection<FSqrLazyProjectDBEntry> allProjects = projectDB.getAllProjects();

        // TODO actually also filter the accessible projects, since they could be starred, before
        //      user lost access.
        List<OutputUserProjectEntry> response = allProjects.stream()//
                        .filter( x -> x.projectIsStarred == true )//
                        .map( this::transform ) //
                        .collect( Collectors.toList() );
        response.sort( new Comparator<OutputUserProjectEntry>() {
            @Override
            public int compare( OutputUserProjectEntry o1, OutputUserProjectEntry o2 ) {
                return o1.project_display_name.compareTo( o2.project_display_name );
            }
        } );

        Gson gson = new Gson();
        return gson.toJson( response );
    }

    @javax.ws.rs.Path( "/allaccessibleprojects" )
    @GET
    @Produces( MediaType.APPLICATION_JSON )
    public <T> String getUserAccessibleProjects( @QueryParam( "userid" ) String userUUID ) {
        Collection<FSqrLazyProjectDBEntry> allProjects = projectDB.getAllProjects();

        // TODO actually also filter the accessible projects, since they could be starred, before
        //      user lost access.
        List<OutputUserProjectEntry> response = allProjects.stream()//
                        .map( this::transform ) //
                        .collect( Collectors.toList() );
        response.sort( new Comparator<OutputUserProjectEntry>() {
            @Override
            public int compare( OutputUserProjectEntry o1, OutputUserProjectEntry o2 ) {
                return o1.project_display_name.compareTo( o2.project_display_name );
            }
        } );
        Gson gson = new Gson();
        return gson.toJson( response );
    }

    private OutputUserProjectEntry transform( FSqrLazyProjectDBEntry project ) {
        OutputUserProjectEntry transformed = new OutputUserProjectEntry();
        transformed.project_id = project.projectID;
        transformed.project_display_name = project.projectDisplayName;
        transformed.description = project.projectDescription;
        transformed.is_starred = project.projectIsStarred;
        return transformed;
    }

    // TODO: /ban
    // TODO: /unban
    // TODO: /add
    // TODO: /adminuserlist
    // TODO: /userdictionary
}