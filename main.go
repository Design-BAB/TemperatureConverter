package main

import (
  "fmt"
  "net/http"
  "html/template"
  "log"
  "strconv"
  "os/exec"
)


func openBrowser(url string) {
	var err error

	err = exec.Command("xdg-open", url).Start()
	if err != nil {
		log.Println("Error opening browser:", err)
	}
}

func fToC(f float64) float64{
  var c float64
  c = f - 32
  factor := 5 / 9.0
  c = c * factor
  return c
}

func cToF(c float64) float64{
  var f float64
  factor := 9 / 5.0
  f = c * factor
  f = f + 32
  return f
}

func main() {
    fmt.Println("Hello! Going to start the web server!")
    http.Handle("/static/", http.StripPrefix("/static/", http.FileServer(http.Dir("static"))))
    //now this is the function keep in mind that h1 means handler #1
    h1 := func (w http.ResponseWriter, r *http.Request) {
      //io.WriteString(w, "Hello World!")
      //io.WriteString(w, r.Method)
      tmp1 := template.Must(template.ParseFiles("index.html"))
      tmp1.Execute(w, nil)
    }



    //This will handle the function.
    //since this is "/" basicly when you go to the homepage it tells the person Hey! go run h1 when visitng the homepage
    http.HandleFunc("/", h1)

   
http.HandleFunc("/convert", func(w http.ResponseWriter, r *http.Request) {
    num1Str := r.URL.Query().Get("number1")
    tempType := r.URL.Query().Get("tempType")

    num1, err1 := strconv.ParseFloat(num1Str, 64)
    if err1 != nil {
        http.Error(w, "Invalid input. Please enter valid numbers.", http.StatusBadRequest)
        return
    }

    w.Header().Set("Content-Type", "text/html")

    var result float64
    if tempType == "Fahrenheit" {
        result = fToC(num1)
        w.Write([]byte("<strong>The temperature is " + strconv.FormatFloat(result, 'f', 1, 64) + " in Celsius. </strong>"))
    } else if tempType == "Celsius" {
        result = cToF(num1)
        w.Write([]byte("<strong>The temperature is " + strconv.FormatFloat(result, 'f', 1, 64) + " in Fahrenheit. </strong>"))
    } else {
        http.Error(w, "Please select a conversion type", http.StatusBadRequest)
    }
})

    http.HandleFunc("/form-label", func(w http.ResponseWriter, r *http.Request) {
      tempType := r.URL.Query().Get("tempType")

      w.Header().Set("Content-Type", "text/html")

      if tempType == "Celsius" {
        w.Write([]byte(`
            <label for="number1">Celsius: </label>
            <input type="number" id="number1" name="number1" min="-100"><br>
        `))
      } else {
        w.Write([]byte(`
            <label for="number1">Fahrenheit: </label>
            <input type="number" id="number1" name="number1" min="-100"><br>
        `))
      }
    })

    openBrowser("http://localhost:8000")
    //log.fatal will recond something if it failed to make a webserver
    //http listen and serve creates that server.
    log.Fatal(http.ListenAndServe(":8000", nil)) 
  }
