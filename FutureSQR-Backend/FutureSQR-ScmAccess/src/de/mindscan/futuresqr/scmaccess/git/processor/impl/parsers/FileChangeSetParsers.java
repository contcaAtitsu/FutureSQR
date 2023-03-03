    private static final String GIT_DIFF_NEW_FILE_MODE = "new file mode ";
    public static void parseIndexLineToFileChangeSet( String currentIndexLine, ScmFileChangeSet currentFileChangeSet ) {
        String[] fileinfo = currentIndexLine.trim().split( " ", 3 );
        String revisions = fileinfo[1];
        String[] revisioninfo = revisions.split( "\\.\\.", 2 );
        currentFileChangeSet.fileParentRevId = revisioninfo[0];
        currentFileChangeSet.fileCurrentRevId = revisioninfo[1];

        if (fileinfo.length > 2) {
            String filemode = fileinfo[2];
            currentFileChangeSet.fileMode = filemode;
        }
    }

    public static void parseNewFileModeLineToFileChangeSet( String newFileModeLine, ScmFileChangeSet currentFileChangeSet ) {
        String fileMode = newFileModeLine.substring( GIT_DIFF_NEW_FILE_MODE.length() );
        currentFileChangeSet.fileMode = fileMode.trim();
    }
