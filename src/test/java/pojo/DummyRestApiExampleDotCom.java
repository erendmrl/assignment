package pojo;

import java.util.List;

public class DummyRestApiExampleDotCom {
    private List<Data> data;

    private String status;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "[data = "+data+", status = "+status+"]";
    }
}
