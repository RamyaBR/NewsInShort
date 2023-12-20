import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.news.newsinshort.R
import com.news.newsinshort.data.entity.Article
import com.news.newsinshort.data.entity.NewsResponse
import com.news.newsinshort.ui.theme.Purple40

@Composable
fun ProgressLoader(){
    Column( modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }
}

@Composable
fun NewsRowComponent(page:Int, article: Article){
   Column(modifier = Modifier
       .fillMaxSize()
       .padding(8.dp)
       .background(Color.White))
       {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article.urlToImage,
            contentScale = ContentScale.Fit,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.logo)
        )
           Spacer(modifier = Modifier.size(20.dp))
           HeadingTextComponent(textvalue = article.title?: "")
           Spacer(modifier = Modifier.size(10.dp))
           NormalTextComponent(textvalue = article.description?: "")
           Spacer(modifier = Modifier.weight(1f))
           AuthorDetailsComponent(article.author, article.source?.name)
   }
}

@Preview
@Composable
fun NewsRowComponentPreview(){
    val article = Article(
        author = "Ms Y",
        title= "Hello Dummy news article ",
        description = "US Treasury yields fall and stocks rise after central bank officials forecast 75bp reduction next year\n",
        null,
        "https://media.cnn.com/api/v1/images/stellar/prod/231211111547-02-detroit-synagogue-police-1021.jpg?c=16x9&q=w_800,c_fill",
        null,
        null,
        null )
    NewsRowComponent(0, article)
}

@Composable
fun HeadingTextComponent(textvalue: String, centerAligned:Boolean = false){
    Text( modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp), text = textvalue,
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        ),
        textAlign = if(centerAligned) TextAlign.Center else TextAlign.Start
    )
}

@Composable
fun NormalTextComponent(textvalue: String){
    Text( modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(8.dp), text = textvalue,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun AuthorDetailsComponent(authorName: String? , sourceName: String?){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 40.dp)){
        authorName?.also {
            Text(text = it)
        }

        Spacer(modifier = Modifier.weight(1f))

        sourceName?.also {
            Text(text = it)
        }
    }
}

@Composable
fun EmptyyStateComponent(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(painter = painterResource(id = R.drawable.nodata),
            contentDescription = null)

        HeadingTextComponent(textvalue = stringResource(R.string.no_news_as_of_now_please_check_in_some_time), true)
    }
}

@Preview
@Composable
fun EmptyyStateComponentPreview(){
    EmptyyStateComponent()
}