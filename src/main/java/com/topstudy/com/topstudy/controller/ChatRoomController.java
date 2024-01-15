package com.topstudy.com.topstudy.controller;

import com.topstudy.com.topstudy.dto.ChatMessageDTO;
import com.topstudy.com.topstudy.dto.ChatRoomDTO;
import com.topstudy.com.topstudy.model.ChatMessage;
import com.topstudy.com.topstudy.model.ChatRoom;
import com.topstudy.com.topstudy.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat-rooms")
public class ChatRoomController {

    @Autowired
    private ChatRoomService chatRoomService; // You'll need to create this service

    // Create a new chat room
    @PostMapping
    public ResponseEntity<ChatRoom> createChatRoom(@RequestBody ChatRoomDTO chatRoomDTO) {
        ChatRoom createdChatRoom = chatRoomService.createChatRoom(chatRoomDTO);
        return new ResponseEntity<>(createdChatRoom, HttpStatus.CREATED);
    }

    // Send a message in a chat room
    @PostMapping("/{room_id}/messages")
    public ResponseEntity<ChatMessage> sendMessage(
            @PathVariable("room_id") Long roomId,
            @RequestBody ChatMessageDTO chatMessageDTO) {
        ChatMessage sentMessage = chatRoomService.sendMessage(roomId, chatMessageDTO);
        if (sentMessage != null) {
            return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve chat messages in a room
    @GetMapping("/{room_id}/messages")
    public ResponseEntity<List<ChatMessage>> getChatMessages(
            @PathVariable("room_id") Long roomId) {
        List<ChatMessage> chatMessages = chatRoomService.getChatMessages(roomId);
        if (!chatMessages.isEmpty()) {
            return new ResponseEntity<>(chatMessages, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
