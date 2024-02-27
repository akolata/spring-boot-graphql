package pl.akolata.graphql.books.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CreateBookInput {
    @NotNull
    private String title;
    private Set<String> authorsIds = new HashSet<>();
}
