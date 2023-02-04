    private static final String GIT_DIFF_INDEX_IDENTIFIER = "index";
    private static final String GIT_DIFF_BINARY_FILES_IDENTIFIER = "Binary files";

    private static final String GIT_DIFF_LEFT_FILEPATH_IDENTIFIER = "---";
    private static final String GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER = "+++";

    private static final String GIT_DIFF_CONTENT_CHANGESET_IDENTIFIER = "@@ ";

    private static final String GIT_DIFF_NO_NEWLINE_AT_END_OF_FILE_INDICATOR = "\\ No newline at end of file";

            // TODO: parse and consume this info and add info to current file change set.
            // TODO: parse and consume this info and add info to current file change set.
        // ----------------------------

        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_INDEX_IDENTIFIER )) {
            // TODO: parse and consume this info and add info to current file change set.            
            currentFileChangeSet.lazy_index_line = lineLexer.consumeCurrentLine();
        }
        else {
            currentFileChangeSet.lazy_index_line = "(empty)";
        }

        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_BINARY_FILES_IDENTIFIER )) {
            // TODO: parse and consume this info and add info to current file change set.            
            currentFileChangeSet.isBinaryFile = true;
            currentFileChangeSet.binary_file_info_line = lineLexer.consumeCurrentLine();
        }

        // ----------------------------
        // left and right file path
        // ----------------------------
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_LEFT_FILEPATH_IDENTIFIER )) {
            // TODO: parse and consume this info and add info to current file change set.
            lineLexer.consumeCurrentLine();
        }
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER )) {
            // TODO: parse and consume this info and add info to current file change set.
            lineLexer.consumeCurrentLine();
        }

        if (!lineLexer.hasNextLine()) {
            fileChangeSetConsumer.accept( currentFileChangeSet );
            return;
        }
        if (currentFileChangeSet.isBinaryFile) {
            fileChangeSetConsumer.accept( currentFileChangeSet );
            return;
        }

        // content changeset identifier is @@ at start
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_CONTENT_CHANGESET_IDENTIFIER )) {

            // while a first menge of a content changeset is found, we will trigger parseContentChangeSetEntry
            while (lineLexer.hasNextLine() && lineLexer.peekCurrentLine().startsWith( GIT_DIFF_CONTENT_CHANGESET_IDENTIFIER )) {
                parseContentChangeSetEntry( lineLexer, currentFileChangeSet.fileContentChangeSet::add );
            }
        }
    private void parseContentChangeSetEntry( GitScmLineBasedLexer lineLexer, Consumer<ScmFileContentChangeSet> contentChangeSetConsumer ) {
        // double check in case we change the outer parser.
        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_CONTENT_CHANGESET_IDENTIFIER )) {
            // TODO: parse and consume this info and add info to current file change set.            
            contentChangeset.line_info = lineLexer.consumeCurrentLine();
        }

        // we must stop at end of input, start at new file info 
        while (lineLexer.hasNextLine() && !lineLexer.peekCurrentLine().startsWith( GIT_DIFF_FILENAMEINFO_IDENTIFIER )
                        && !lineLexer.peekCurrentLine().startsWith( GIT_DIFF_CONTENT_CHANGESET_IDENTIFIER )) {

            String currentLine = lineLexer.consumeCurrentLine();
            if (currentLine.startsWith( GIT_DIFF_NO_NEWLINE_AT_END_OF_FILE_INDICATOR )) {
                //intentionally left blank;
            }
            else {
                // TODO: it either starts with a '+', '-', ' ' this should be handled and split into a different Array, 
                // where add, remove and unchanged info is stored for each index. 
                contentChangeset.line_diff_data.add( currentLine );
            }
        }
