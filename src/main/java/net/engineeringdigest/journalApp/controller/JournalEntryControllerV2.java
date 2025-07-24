package net.engineeringdigest.journalApp.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
@Tag(name="Journal APIs") // for swagger UI
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;


    @PostMapping
    public ResponseEntity<JournalEntry> createJournal(@RequestBody JournalEntry newJournal) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(newJournal, userName);
            return new ResponseEntity<>(newJournal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<JournalEntry>> getAllJournalOfUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        List<JournalEntry> allEntries = journalEntryService.getEntriesByUserName(userName);
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        List<JournalEntry> entriesByUserName = journalEntryService.getEntriesByUserName(userName);
        List<JournalEntry> collect = entriesByUserName
                .stream()
                .filter(entry -> entry.getId().equals(objectId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {
            Optional<JournalEntry> entryById = journalEntryService.getEntryById(objectId);
            if (entryById.isPresent()) {
                return new ResponseEntity<>(entryById.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable String id) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        boolean removed = journalEntryService.deleteEntry(objectId, userName);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntry(
            @PathVariable String id,
            @RequestBody JournalEntry updateJournalEntry
    ) {
        ObjectId objectId = new ObjectId(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        List<JournalEntry> entriesByUserName = journalEntryService.getEntriesByUserName(userName);
        List<JournalEntry> collect = entriesByUserName
                .stream()
                .filter(entry -> entry.getId().equals(objectId))
                .collect(Collectors.toList());

        if (!collect.isEmpty()) {

            Optional<JournalEntry> entryById = journalEntryService.getEntryById(objectId);
            if (entryById.isPresent()) {
                JournalEntry oldEntry = entryById.get();

                oldEntry.setTitle(
                        updateJournalEntry.getTitle() != null &&
                                !updateJournalEntry.getTitle().isEmpty()
                                ? updateJournalEntry.getTitle()
                                : oldEntry.getTitle()
                );
                oldEntry.setContent(
                        updateJournalEntry.getContent() != null && !updateJournalEntry.getContent().isEmpty()
                                ? updateJournalEntry.getContent()
                                : oldEntry.getContent()
                );

                JournalEntry saveEntry = journalEntryService.saveEntry(oldEntry);
                return new ResponseEntity<>(saveEntry, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
