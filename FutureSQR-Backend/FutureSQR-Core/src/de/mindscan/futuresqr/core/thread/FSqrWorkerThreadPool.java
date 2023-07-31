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
package de.mindscan.futuresqr.core.thread;

/**
 * 
 */
public class FSqrWorkerThreadPool {

    /**
     * 
     */
    public FSqrWorkerThreadPool( int threadPoolSize ) {
        // TODO create threads and then put them into the created threads queue
    }

    // TODO: initialize threadpool
    // we move each thread from created, tell each thread that it is now pooled and 
    // put them into the pooled queue

    // TODO BORROW...
    public FSqrWorkerThread borrowThread() {
        // TODO we look for a pooled thread in the pooled list, and pull the first. (synchronized)

        // we tell the thread that this thread is now borrowed.
        // we add the thread to the borrowed queue

        return null;
    }

    // TODO finishedThread()
    // we take it from the borrowed queue finished queue

    // TODO collectFinishedThreads
    // we take all threads from the finished queue declare them pooled and add them to the pooled Deque

}