package com.topstudy.com.topstudy.service;

import com.topstudy.com.topstudy.dto.ChatMessageDTO;
import com.topstudy.com.topstudy.dto.ChatRoomDTO;
import com.topstudy.com.topstudy.model.ChatMessage;
import com.topstudy.com.topstudy.model.ChatRoom;
import com.topstudy.com.topstudy.repository.ChatMessageRepository;
import com.topstudy.com.topstudy.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository; // You'll need to create this repository

    @Autowired
    private ChatMessageRepository chatMessageRepository; // You'll need to create this repository

    @Transactional
    public ChatRoom createChatRoom(ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setName(chatRoomDTO.getName());
        return chatRoomRepository.save(chatRoom);
    }

    @Transactional
    public ChatMessage sendMessage(Long roomId, ChatMessageDTO chatMessageDTO) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElse(null);

        if (chatRoom != null) {
            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setChatRoom(chatRoom);
            chatMessage.setContent(chatMessageDTO.getContent());
            return chatMessageRepository.save(chatMessage);
        }

        return null; // Return null if the chat room with the specified ID is not found
    }

    @Transactional(readOnly = true)
    public List<ChatMessage> getChatMessages(Long roomId) {
        return chatMessageRepository.findByChatRoomId(roomId);
    }
}
