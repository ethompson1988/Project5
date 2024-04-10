package  com.jobcatalog.jobcatalog.web;

import com.jobcatalog.jobcatalog.domain.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class JobJsonTests {

    @Autowired
    private JacksonTester<Job> json;

    @Test
    void testSerialize() throws Exception {
        var job = new Job(123456, "Title", "Description", "CompanyName", "Skill1", "Skill2");
        var jsonContent = json.write(job);
        assertThat(jsonContent).extractingJsonPathNumberValue("@.JobId")
                .isEqualTo(123456);
//                .isEqualTo(job.JobId());
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(job.title());
//        System.out.println("@.title");
        assertThat(jsonContent).extractingJsonPathStringValue("@.description")
                .isEqualTo(job.description());
        assertThat(jsonContent).extractingJsonPathStringValue("@.companyName")
                .isEqualTo(job.companyName());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill1")
                .isEqualTo(job.skill1());
        assertThat(jsonContent).extractingJsonPathStringValue("@.skill2")
                .isEqualTo(job.skill2());
    }
    @Test
    void testDeserialize() throws Exception {
        var content = """
            {
                "JobId" : 123456,
                "title" : "Title",
                "description" : "Description",
                "companyName" : "CompanyName",
                "skill1" : "Skill1",
                "skill2" : "Skill2"
            }
            """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Job(123456, "Title", "Description", "CompanyName", "Skill1", "Skill2"));
    }
}