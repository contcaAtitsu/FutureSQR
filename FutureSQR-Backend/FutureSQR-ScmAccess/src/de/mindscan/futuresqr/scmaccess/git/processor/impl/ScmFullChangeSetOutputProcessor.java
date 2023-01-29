import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import de.mindscan.futuresqr.scmaccess.types.ScmFileChangeSet;
import de.mindscan.futuresqr.scmaccess.types.ScmFileContentChangeSet;
    private static final String GIT_DIFF_FILENAMEINFO_IDENTIFIER = "diff --git ";
    private static final String GIT_DIFF_NEW_FILE_MODE = "new file mode ";
    private static final String GIT_DIFF_DELETED_FILE_MODE = "deleted file mode";

    private static final String GIT_DIFF_RENAME_SIMILARITY_INDEX = "similarity index";
    private static final String GIT_DIFF_RENAME_FROM = "rename from";
    private static final String GIT_DIFF_RENAME_TO = "rename to";

        ScmFullChangeSet scmFullChangeSet = new ScmFullChangeSet();

        // TODO: analyze the standard changesets, UTF_8 ...
        // TODO: Actually each file can have it's own encoding, such that we must provide a scanner with different charset modes
        // this needs to be fixed longer term.
        parseFullChangeSet( new String( output.getProcessOutput(), StandardCharsets.UTF_8 ), scmFullChangeSet.fileChangeSet::add );

        return scmFullChangeSet;
    }

    private void parseFullChangeSet( String string, Consumer<ScmFileChangeSet> fileChangeSetConsumer ) {
        // TODO: Actually the split tokens should not be consumed from the string
        //       can to this later.
        GitScmLineBasedLexer lineLexer = new GitScmLineBasedLexer( string.split( "\\R" ) );

        while (lineLexer.hasNextLine()) {
            String currentLine = lineLexer.peekCurrentLine();

            // peek at current line
            if (currentLine.startsWith( GIT_DIFF_FILENAMEINFO_IDENTIFIER )) {
                // for each file file entry
                System.out.println( "[parseFullChangeSet][p]: '" + currentLine + "'" );
                parseFileChangeSetEntry( lineLexer, fileChangeSetConsumer );
            }
            else {
                // This will either skip some header or unparsed stuff from the inner parsers.
                System.out.println( "[parseFullChangeSet][u]: '" + currentLine + "'" );
                lineLexer.advanceToNextLine();
            }
        }

        // first menge for file entry will trigger parseFileChangeSetEntry
    }

    private void parseFileChangeSetEntry( GitScmLineBasedLexer lineLexer, Consumer<ScmFileChangeSet> fileChangeSetConsumer ) {
        // TOOD: binary mode is false;

        // create a new file entry
        ScmFileChangeSet currentFileChangeSet = new ScmFileChangeSet();

        // Parse the file info
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_FILENAMEINFO_IDENTIFIER )) {
            // TODO: parse and consume this file name info identifier, such that this info is in the currentFileChangeSet
            currentFileChangeSet.lazy_diff_line = lineLexer.consumeCurrentLine();
        }

        // ---------------------
        // parse file mode
        // ---------------------

        // Parse new file mode
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_NEW_FILE_MODE )) {
            // TODO: parse and consume this info and add info to current file change set.
            lineLexer.consumeCurrentLine();
        }
        // Parse deleted file mode
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_DELETED_FILE_MODE )) {
            // TODO: parse and consume this info and add info to current file change set.
            lineLexer.consumeCurrentLine();
        }

        // ---------------------
        // parse renames
        // ---------------------

        // parse similarity of rename / move operation 
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_RENAME_SIMILARITY_INDEX )) {
            // TODO: parse and consume this info and add info to current file change set.
            currentFileChangeSet.similarity_info_line = lineLexer.consumeCurrentLine();
        }

        // parse from name / from directory
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_RENAME_FROM )) {
            currentFileChangeSet.renamed_from = lineLexer.consumeCurrentLine();
        }

        // parse to name / to directory
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_RENAME_TO )) {
            currentFileChangeSet.renamed_to = lineLexer.consumeCurrentLine();
        }

        // ----------------------------
        // index line... / binary files
        // ----------------------------        

        // while a first menge of a content changeset is found, we will trigger parseContentChangeSetEntry
        parseContentChangeSetEntry( currentFileChangeSet.fileContentChangeSet::add );

        // end of stream or
        // first menge of a file changeset will finish scanning this file entry 

        // result is a file entry in the ScmFullChangeSet
        fileChangeSetConsumer.accept( currentFileChangeSet );
    }

    private void parseContentChangeSetEntry( Consumer<ScmFileContentChangeSet> contentChangeSetConsumer ) {
        // this is the parsing of a single
        ScmFileContentChangeSet contentChangeset = new ScmFileContentChangeSet();

        contentChangeSetConsumer.accept( contentChangeset );