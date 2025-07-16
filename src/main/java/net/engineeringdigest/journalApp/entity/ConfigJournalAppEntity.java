package net.engineeringdigest.journalApp.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigJournalAppEntity {
    @Id
    private ObjectId id;

    @NonNull
    private String key;

    @NonNull
    private String value;

}
