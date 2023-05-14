    private static final String GIT_DIFF_A_SLASH_PATH = GitOutputParsingConstants.GIT_DIFF_A_SLASH_PATH;
    private static final String GIT_DIFF_SAPCE_A_SLASH_PATH = GitOutputParsingConstants.GIT_DIFF_SAPCE_A_SLASH_PATH;
    private static final String GIT_DIFF_B_SLASH_PATH = GitOutputParsingConstants.GIT_DIFF_B_SLASH_PATH;
    private static final String GIT_DIFF_SPACE_B_SLASH_PATH = GitOutputParsingConstants.GIT_DIFF_SPACE_B_SLASH_PATH;
    private static final String GIT_DIFF_FILENAMEINFO_IDENTIFIER = GitOutputParsingConstants.GIT_DIFF_FILENAMEINFO_IDENTIFIER;
    private static final String GIT_DIFF_NEW_FILE_MODE = GitOutputParsingConstants.GIT_DIFF_NEW_FILE_MODE;
    private static final String GIT_DIFF_DELETED_FILE_MODE = GitOutputParsingConstants.GIT_DIFF_DELETED_FILE_MODE;
    private static final String GIT_DIFF_RENAME_SIMILARITY_INDEX = GitOutputParsingConstants.GIT_DIFF_RENAME_SIMILARITY_INDEX;

    private static final String GIT_DIFF_LEFT_FILEPATH_IDENTIFIER = GitOutputParsingConstants.GIT_DIFF_LEFT_FILEPATH_IDENTIFIER;
    private static final String GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER = GitOutputParsingConstants.GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER;

    private static final String GIT_DIFF_RENAME_TO = GitOutputParsingConstants.GIT_DIFF_RENAME_TO;
    private static final String GIT_DIFF_RENAME_FROM = GitOutputParsingConstants.GIT_DIFF_RENAME_FROM;

    private static final String GIT_DIFF_BINARY_FILES_IDENTIFIER = GitOutputParsingConstants.GIT_DIFF_BINARY_FILES_IDENTIFIER;

    private static final String GIT_DEV_NULL = "/dev/null";
        int firstIndexASlash = currentGitDiffLine.indexOf( GIT_DIFF_SAPCE_A_SLASH_PATH );
        int firstIndexBSlash = currentGitDiffLine.indexOf( GIT_DIFF_SPACE_B_SLASH_PATH );
        int lastIndexBSlash = currentGitDiffLine.lastIndexOf( GIT_DIFF_SPACE_B_SLASH_PATH );
        currentFileChangeSet.scmFromPath = currentGitDiffLine.substring( firstIndexASlash + GIT_DIFF_SAPCE_A_SLASH_PATH.length(), lastIndexBSlash );
        currentFileChangeSet.scmToPath = currentGitDiffLine.substring( firstIndexBSlash + GIT_DIFF_SPACE_B_SLASH_PATH.length() );
    public static void parseLeftFilePath( String leftFilePathLine, ScmFileChangeSet currentFileChangeSet ) {
        if (leftFilePathLine.startsWith( GIT_DIFF_LEFT_FILEPATH_IDENTIFIER )) {

            String leftFilePath = leftFilePathLine.substring( GIT_DIFF_LEFT_FILEPATH_IDENTIFIER.length() ).trim();

            if (leftFilePath.startsWith( GIT_DIFF_A_SLASH_PATH )) {
                leftFilePath = leftFilePath.substring( GIT_DIFF_A_SLASH_PATH.length() );
            }

            if ((leftFilePath.length() != 0) && !GIT_DEV_NULL.equals( leftFilePath ) && !leftFilePath.equals( currentFileChangeSet.scmFromPath )) {
                // System.out.println( "XXX: Differs from scmFromPath: '" + leftFilePath + "' instead of: '" + currentFileChangeSet.scmFromPath + "'" );
                // correcting file path - because parsing the diff --git line may not be robust enough 
                currentFileChangeSet.scmFromPath = leftFilePath;
            }
        }
    }

    public static void parseRightFilePath( String rightFilePathLine, ScmFileChangeSet currentFileChangeSet ) {
        if (rightFilePathLine.startsWith( GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER )) {

            String rightFilePath = rightFilePathLine.substring( GIT_DIFF_RIGHT_FILEPATH_IDENTIFIER.length() ).trim();

            if (rightFilePath.startsWith( GIT_DIFF_B_SLASH_PATH )) {
                rightFilePath = rightFilePath.substring( GIT_DIFF_B_SLASH_PATH.length() );
            }

            if ((rightFilePath.length() != 0) && !GIT_DEV_NULL.equals( rightFilePath ) && !rightFilePath.equals( currentFileChangeSet.scmToPath )) {
                // System.out.println( "XXX: Differs from scmToPath: '" + rightFilePath + "' instead of: '" + currentFileChangeSet.scmToPath + "'" );
                // correcting file path - because parsing the diff --git line may not be robust enough
                currentFileChangeSet.scmToPath = rightFilePath;
            }
        }
    }

    public static void parseBinaryFileInfoLine( String binaryFileInfoLine, ScmFileChangeSet currentFileChangeSet ) {
        if (binaryFileInfoLine.startsWith( GIT_DIFF_BINARY_FILES_IDENTIFIER )) {
            String binaryInfoLineData = binaryFileInfoLine.substring( GIT_DIFF_BINARY_FILES_IDENTIFIER.length() ).trim();

            // TODO: we still need to do something with it.
            System.out.println( "XXX: binaryInfoLineData: " + binaryInfoLineData );

            currentFileChangeSet.isBinaryFile = true;
            currentFileChangeSet.binary_file_info_line = binaryInfoLineData;
        }
    }

    public static void parseRenameTo( String renameToLine, ScmFileChangeSet currentFileChangeSet ) {
        if (renameToLine.startsWith( GIT_DIFF_RENAME_TO )) {
            String renameToFilename = renameToLine.substring( GIT_DIFF_RENAME_TO.length() ).trim();
            currentFileChangeSet.renamed_to = renameToFilename;
        }
    }

    public static void parseRenameFrom( String renameFromLine, ScmFileChangeSet currentFileChangeSet ) {
        if (renameFromLine.startsWith( GIT_DIFF_RENAME_FROM )) {
            String renameFromFilename = renameFromLine.substring( GIT_DIFF_RENAME_FROM.length() ).trim();
            currentFileChangeSet.renamed_from = renameFromFilename;
        }
    }

    public static void parseDeleteFileModeLineToFileChangeSet( String deleteFileModeLine, ScmFileChangeSet currentFileChangeSet ) {
        // TODO: parse and consume this info and add info to current file change set.
        if (deleteFileModeLine.startsWith( GIT_DIFF_DELETED_FILE_MODE )) {

        }
    }
