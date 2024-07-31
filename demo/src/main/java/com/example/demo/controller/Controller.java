package com.example.demo.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tutorial")
public class Controller {
    @Autowired
    private VectorStore vectorStore;

    @GetMapping("/add")
    public String addDocuments() {
        List<Document> docs = List.of(
                new Document("Proper tuber planting involves site selection, proper timing, and exceptional care. Choose spots with well-drained soil and adequate sun exposure. Tubers are generally planted in spring, but depending on the plant, timing varies. Always plant with the eyes facing upward at a depth two to three times the tuber's height. Ensure 4 inch spacing between small tubers, expand to 12 inches for large ones. Adequate moisture is needed, yet do not overwater. Mulching can help preserve moisture and prevent weed growth.", Map.of("author", "A", "type","post")),
                new Document("Successful oil painting necessitates patience, proper equipment, and technique. Begin with a carefully prepared, primed canvas. Sketch your composition lightly before applying paint. Use high-quality brushes and oils to create vibrant, long-lasting artworks. Remember to paint 'fat over lean,' meaning each subsequent layer should contain more oil to prevent cracking. Allow each layer to dry before applying another. Clean your brushes often and avoid solvents that might damage them. Finally, always work in a well-ventilated space.", Map.of("author", "A")),
                new Document("For a natural lawn, selection of the right grass type suitable for your climate is crucial. Balanced watering, generally 1 to 1.5 inches per week, is important; overwatering invites disease. Opt for organic fertilizers over synthetic versions to provide necessary nutrients and improve soil structure. Regular lawn aeration helps root growth and prevents soil compaction. Practice natural pest control and consider overseeding to maintain a dense sward, which naturally combats weeds and pest.", Map.of("author", "B", "type","post"))
        );

        vectorStore.add(docs);
        return "Documents added successfully!\n";
    }

    @GetMapping("/search")
    public List<Map<String, Object>> searchDocuments() {

        List<Document> results = vectorStore.similaritySearch(
                SearchRequest
                        .query("learn how to grow things")
                        .withTopK(2)
        );

//        Metadata filtering

//         FilterExpressionBuilder b = new FilterExpressionBuilder();

//         List<Document> results = vectorStore.similaritySearch(
//                SearchRequest.defaults()
//                        .withQuery("learn how to grow things")
//                        .withTopK(2)
//                        .withSimilarityThreshold(0.5)
//                        .withFilterExpression(b.eq("author", "A").build())
//         );

        return results.stream().map(doc -> Map.of(
                "content", doc.getContent(),
                "metadata", doc.getMetadata()
        )).collect(Collectors.toList());
    }
}
