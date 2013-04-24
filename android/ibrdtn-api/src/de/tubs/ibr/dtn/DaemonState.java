/*
 * DaemonState.java
 * 
 * Copyright (C) 2011 IBR, TU Braunschweig
 *
 * Written-by: Johannes Morgenroth <morgenroth@ibr.cs.tu-bs.de>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package de.tubs.ibr.dtn;

import android.os.Parcel;
import android.os.Parcelable;

public enum DaemonState implements Parcelable {
	UNKOWN,
	PENDING,
	ONLINE,
	OFFLINE,
	SUSPENDED,
	ERROR;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(ordinal());
    }

    public static final Creator<DaemonState> CREATOR = new Creator<DaemonState>() {
        public DaemonState createFromParcel(final Parcel source) {
            return DaemonState.values()[source.readInt()];
        }

        public DaemonState[] newArray(final int size) {
            return new DaemonState[size];
        }
    };
}
