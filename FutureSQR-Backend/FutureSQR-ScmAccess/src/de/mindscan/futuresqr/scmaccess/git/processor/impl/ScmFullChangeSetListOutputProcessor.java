import de.mindscan.futuresqr.scmaccess.git.processor.impl.parsers.FullChangeSetParsers;
            String commitRevisionIdLine = lineLexer.consumeCurrentLine();
            FullChangeSetParsers.parseCommitRevisionLineToFullChangeSet( commitRevisionIdLine, scmFullChangeSet );
            String similarityLine = lineLexer.consumeCurrentLine();
            FileChangeSetParsers.parseRenameSimilarityToFileChangeSet( similarityLine, currentFileChangeSet );