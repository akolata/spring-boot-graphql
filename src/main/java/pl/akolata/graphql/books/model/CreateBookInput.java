package pl.akolata.graphql.books.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreateBookInput {
    private String title;
    private Set<String> authorsIds = new HashSet<>();
}
