import de.mindscan.futuresqr.scmaccess.git.processor.impl.parsers.ContentChangeSetParsers;
import de.mindscan.futuresqr.scmaccess.git.processor.impl.parsers.FileChangeSetParsers;
    private static final String GIT_DIFF_NEWCOMMIT_COMMIT_IDENTIFIER = "commit ";
    private static final String GIT_DIFF_NEWCOMMIT_AUTHOR_LINE_IDENTIFIER = "Author: ";
    private static final String GIT_DIFF_NEWCOMMIT_DATE_LINE_IDENTIFIER = "Date: ";

    private static final String GIT_DIFF_SECTION_SPLITTER = "\n\n";

        List<ScmFullChangeSet> resultList = parseFullChangeSetList( new String( output.getProcessOutput(), StandardCharsets.UTF_8 ) );
    private List<ScmFullChangeSet> parseFullChangeSetList( String string ) {
        List<ScmFullChangeSet> resultList = new ArrayList<>();
        // For debug purposes 
        System.out.println( string );

        // prepare line lexxer
        // TODO NEXT: must split to new commit, we get the diff for each single commit in between. not what I expected.....
        // TODO NEXT: maybe a list of full change sets for each revision one entry in the list.

            // peek at current line
            if (currentLine.startsWith( GIT_DIFF_NEWCOMMIT_COMMIT_IDENTIFIER )) {
                // for each commit identifier
                System.out.println( "[parseFullChangeSetList][p]: '" + currentLine + "'" );
                resultList.add( parseFullChangeSet( lineLexer ) );
            }
            else {
                // This will either skip some header or unparsed stuff from the inner parsers.
                System.out.println( "[parseFullChangeSetList][u]: '" + currentLine + "'" );
                lineLexer.advanceToNextLine();
            }
        }

        return resultList;
    }

    private ScmFullChangeSet parseFullChangeSet( GitScmLineBasedLexer lineLexer ) {
        // Parse the revision, then parse file until next revision - initialize another revision continue to parse the 
        // revision

        // TODO: parse the commit + id /Author/date/message andmaybe also provide a list of full changesets .... instead of one.
        ScmFullChangeSet scmFullChangeSet = new ScmFullChangeSet();
        Consumer<ScmFileChangeSet> fileChangeSetConsumer = scmFullChangeSet.fileChangeSet::add;

        if (lineLexer.peekCurrentLine().startsWith( GIT_DIFF_NEWCOMMIT_COMMIT_IDENTIFIER )) {
            // TODO parse commit identifier
            lineLexer.consumeCurrentLine();
        }

        // TODO: Author
        // TODO: DATE
        // TODO: NEWLINE
        // TODO: commitmessage (starts with 4 spaces "    ")
        // TODO: NEWLINE

        while (lineLexer.hasNextLine() && !lineLexer.peekCurrentLine().startsWith( GIT_DIFF_NEWCOMMIT_COMMIT_IDENTIFIER )) {
            String currentLine = lineLexer.peekCurrentLine();

                fileChangeSetConsumer.accept( parseFileChangeSetEntry( lineLexer ) );
        return scmFullChangeSet;
    private ScmFileChangeSet parseFileChangeSetEntry( GitScmLineBasedLexer lineLexer ) {
        currentFileChangeSet.isBinaryFile = false;
            String currentLazyDiffLine = lineLexer.consumeCurrentLine();
            FileChangeSetParsers.parseGitDiffLineToFileChangeSet( currentLazyDiffLine, currentFileChangeSet );
            return currentFileChangeSet;
            return currentFileChangeSet;
        return currentFileChangeSet;
            String contentChangeSetLineInfo = lineLexer.consumeCurrentLine();
            ContentChangeSetParsers.parseLineInfoIntoContentChangeSet( contentChangeSetLineInfo, contentChangeset );
                        && !lineLexer.peekCurrentLine().startsWith( GIT_DIFF_NEWCOMMIT_COMMIT_IDENTIFIER )