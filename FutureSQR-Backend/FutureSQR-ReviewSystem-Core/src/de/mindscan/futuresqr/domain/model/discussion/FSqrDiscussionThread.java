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
package de.mindscan.futuresqr.domain.model.discussion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 */
public class FSqrDiscussionThread {

    private String discussionThreadUUID = "";
    private String discussionThreadAuthor = "";

    // TODO: inOrderList (by time) or by threaddepth and time?
    private ArrayList<String> messageOrder = new ArrayList<>();

    private Map<String, FSqrDiscussionThreadMessage> discussionMessages = new HashMap<>();

    public FSqrDiscussionThread() {
        // TODO Auto-generated constructor stub
    }

    public FSqrDiscussionThread( String newThreadUUID, FSqrDiscussionThreadMessage rootMessage, String messageAuthorUUID ) {
        this.discussionThreadUUID = newThreadUUID;
        this.discussionThreadAuthor = messageAuthorUUID;
        this.messageOrder = new ArrayList<>();
        this.discussionMessages = new HashMap<>();

        this.addAsRootMessage( rootMessage );
    }

    private void addAsRootMessage( FSqrDiscussionThreadMessage rootMessage ) {
        messageOrder.add( 0, rootMessage.getMessageUUID() );
        discussionMessages.put( rootMessage.getMessageUUID(), rootMessage );
    }

    public String getDiscussionThreadUUID() {
        return discussionThreadUUID;
    }

    public String getDiscussionThreadAuthor() {
        return discussionThreadAuthor;
    }

    public Map<String, FSqrDiscussionThreadMessage> getDiscussionMessages() {
        return discussionMessages;
    }

    public List<String> getMessageOrder() {
        return messageOrder;
    }

    public void updateMessage( String messageUUID, String newMessageText, String messageAuthorUUID ) {
        if (discussionMessages.containsKey( messageUUID )) {
            FSqrDiscussionThreadMessage message = discussionMessages.get( messageUUID );
            message.updateMessage( newMessageText, messageAuthorUUID );
        }
    }

    // TODO: addReplyToMessage()
}