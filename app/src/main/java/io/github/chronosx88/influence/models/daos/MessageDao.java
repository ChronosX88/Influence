/*
 *    Copyright 2019 ChronosX88
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.github.chronosx88.influence.models.daos;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.github.chronosx88.influence.models.roomEntities.MessageEntity;

@Dao
public interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insertMessage(MessageEntity chatModel);

    @Query("DELETE FROM messages WHERE messageID = :messageID")
    void deleteMessage(String messageID);

    @Query("DELETE FROM messages WHERE jid = :jid")
    void deleteMessagesByChatID(String jid);

    @Query("SELECT * FROM messages WHERE jid = :jid")
    List<MessageEntity> getMessagesByChatID(String jid);

    @Query("SELECT * FROM messages WHERE messageID = :messageID")
    List<MessageEntity> getMessageByID(long messageID);

    @Update
    void updateMessage(MessageEntity message);

    @Query("DELETE FROM messages")
    void clearMessages();

    @Query("SELECT messageID FROM messages WHERE jid = :chatID GROUP BY :chatID HAVING MAX(messageID)")
    long getLastMessageByChatID(String chatID);
}
