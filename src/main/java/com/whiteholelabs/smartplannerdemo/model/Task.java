//
// Copyright 2024 (c) Chris Romero.
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the “Software”), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package com.whiteholelabs.smartplannerdemo.model;

/// This is an Activity Assigned to a Worker.
public class Task {
    /// A representative name of this Activity.
    private String title;
    /// The current state of this Activity.
    /// To Do - A pending Activity.
    /// Doing - The Activity is In process.
    /// Done - A finished Activity.
    private int state;

    public String getTitle() {
        return this.title;
    }

    public int getState() {
        return this.state;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(int state) {
        this.state = state;
    }
}
