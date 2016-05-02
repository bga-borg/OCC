package com.occ.infinispan.testmodel;

import org.hibernate.search.annotations.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Indexed
public class Book {
    @Field
    String title;
    @Field String description;
    @Field @DateBridge(resolution= Resolution.YEAR)
    Date publicationYear;
    @IndexedEmbedded
    Set<Author> authors = new HashSet<Author>();
}