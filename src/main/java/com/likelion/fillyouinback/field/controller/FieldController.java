package com.likelion.fillyouinback.field.controller;

        import com.likelion.fillyouinback.field.controller.response.FieldListResponse;
        import lombok.RequiredArgsConstructor;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;
        import com.likelion.fillyouinback.field.service.FieldService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fillyouin/fields")
public class FieldController {
    private final FieldService fieldService;

    @GetMapping
    public ResponseEntity<FieldListResponse> getFieldList() {
        return ResponseEntity.ok(new FieldListResponse(fieldService.getFieldList()));
    }
}
