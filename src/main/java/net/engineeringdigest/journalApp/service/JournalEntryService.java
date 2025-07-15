package net.engineeringdigest.journalApp.service;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    // save journal entry
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);

            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);

            user.getJournalEntries().add(saved);
            userService.saveUser(user);

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving entry ", e);
        }

    }

    // Update Journal Entry
    public JournalEntry saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        return journalEntryRepository.save(journalEntry);
    }

    // Get All Entries
    public List<JournalEntry> getEntriesByUserName(String userName) {
        User user = userService.findByUserName(userName);
        return user.getJournalEntries();
    }


    // Get Entry by Id
    public Optional<JournalEntry> getEntryById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    // Update Journal Entry
    public JournalEntry updateEntryById(ObjectId id, JournalEntry updatedEntry) {
        return journalEntryRepository.save(updatedEntry);
    }

    // Delete Entry
    @Transactional
    public boolean deleteEntry(ObjectId id, String userName) {
        boolean removed = false;
        try{
        User user = userService.findByUserName(userName);
        removed = user.getJournalEntries().removeIf(existingEntry -> existingEntry.getId().equals(id));
        if (removed) {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
            return removed;
        }

        return removed;

        } catch (Exception e ){
            System.out.println(e);
            throw new RuntimeException("Error occurred while deleting entry: ", e);
        }

    }
}
