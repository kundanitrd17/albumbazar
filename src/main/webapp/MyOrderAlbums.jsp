<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
    body {
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        background: #f8f8f8;
    }

    * {
        box-sizing: border-box;
    }

    .center-marker {
        height: 50px;
        width: 100vw;
        position: absolute;
        top: calc(50% - 360px);
        display: none;
    }

    .center-marker div {
        height: 30px;
        width: 1px;
        margin: auto;
        background: #8882;
    }

    .js-slideshow {
        --offset: 1600px;
        --overlap: 2;
        --image-gap: 6;
        --offset-time: 0.48s;
        --thumb-border-radius: 0;
        --thumb-size: 50;
        --thumbs-width: 20000;
        width: calc(100vw - 0px);
        max-width: 880px;
        position: relative;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }

    .js-slideshow__slides-wrapper {
        position: relative;
        height: 0;
        width: 100%;
        padding-bottom: 50%;
        overflow: hidden;
    }

    .js-slideshow__slides {
        position: absolute;
        height: 100%;
        width: 40000px;
        left: 50%;
        transform: translate(calc(var(--offset) * -1), 0);
        display: flex;
        flex-flow: row nowrap;
        justify-content: flex-start;
        transition: transform var(--offset-time) ease;
    }

    .js-slideshow__slides img {
        height: 100%;
        display: block;
        margin-left: calc(var(--image-gap) * 0.5px);
        margin-right: calc(var(--image-gap) * 0.5px);
    }

    .js-slideshow__thumbs-wrapper {
        width: 100%;
        height: calc(var(--thumb-size) * 1px + 18px);
        margin-top: calc(var(--image-gap) * 1px);
        position: relative;
        display: block;
        overflow: scroll;
        scroll-behavior: smooth;
    }

    .js-slideshow__thumbs {
        width: calc(var(--thumbs-width) * 1px);
        display: grid;
        grid-auto-rows: calc(var(--thumb-size) * 1px);
        grid-template-columns: repeat(auto-fit, calc(var(--thumb-size) * 1px));
        grid-gap: calc(var(--image-gap) * 1px);
    }

    .js-slideshow__thumb-image {
        margin: 0;
        position: relative;
    }

    .js-slideshow__thumb-image img {
        height: 100%;
        width: 100%;
        display: block;
        border-radius: var(--thumb-border-radius);
        -o-object-fit: cover;
        object-fit: cover;
    }

    .js-slideshow__thumb-image--selected:after {
        content: "";
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        border-radius: var(--thumb-border-radius);
        box-shadow: inset 0 0 0 3px rgba(50, 200, 200, 0.8);
    }

    @media (min-width: 700px) {
        .js-slideshow {
            --thumb-size: 77;
        }

        .js-slideshow__thumbs-wrapper {
            display: block;
        }
    }
</style>

<body>


    <div class="center-marker">
        <div></div>
    </div>
    <div id="jsSlideshow" data-speed="5" data-transition="0.55" class="js-slideshow">
        <div class="js-slideshow__slides-wrapper">
            <div class="js-slideshow__slides">
                <img src="./img/slide1.svg" alt="">
                <img src="./img/logo.png" alt="">
                <img src="https://images.pexels.com/photos/1081685/pexels-photo-1081685.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/953724/pexels-photo-953724.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/3494806/pexels-photo-3494806.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/973506/pexels-photo-973506.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/1937555/pexels-photo-1937555.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/3375493/pexels-photo-3375493.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/2710282/pexels-photo-2710282.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/258112/pexels-photo-258112.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/3534156/pexels-photo-3534156.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/159613/ghettoblaster-radio-recorder-boombox-old-school-159613.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/792326/pexels-photo-792326.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/1629236/pexels-photo-1629236.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/1089027/pexels-photo-1089027.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/1895849/pexels-photo-1895849.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/1964471/pexels-photo-1964471.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/237272/pexels-photo-237272.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/3196534/pexels-photo-3196534.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/219906/pexels-photo-219906.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/45204/alm-friuli-snow-snowfall-45204.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/326055/pexels-photo-326055.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/2129918/pexels-photo-2129918.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
                <img src="https://images.pexels.com/photos/266688/pexels-photo-266688.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260"
                    alt="">
            </div>
        </div>
    </div>
    <script>const clickSlide = () => {
            const clickPos = event.clientX / window.innerWidth;
            if (clickPos >= 0.5) {
                incImage(1);
            } else {
                incImage(-1);
            }
        };

        const handleOverlap = newImage => {
            toggleEventListeners(0);
            setTimeout(() => {
                toggleTransition(0);
                whichImage = newImage;
                updateImage();
                setTimeout(() => {
                    toggleTransition(1);
                    toggleEventListeners(1);
                }, 200);
            }, slideTransition);
        };

        const detectOverlap = () => {
            const minImage = overlap;
            const maxImage = imageArray.length - overlap - 1;
            const moveBy = imageArray.length - overlap * 2;
            updateImage();
            if (whichImage > maxImage) {
                handleOverlap(whichImage - moveBy);
            } else if (whichImage < minImage) {
                handleOverlap(whichImage + moveBy);
            }
        };

        const incImage = (incAmount = 1) => {
            whichImage += incAmount;
            detectOverlap();
        };

        const createDopplegangers = ovr => {
            const slides = slideshowSlides.children;
            const cloneStart = [];
            const cloneEnd = [];
            for (let i = 0; i < ovr; i++) {
                cloneStart.push(slides[i].cloneNode(true));
                cloneEnd.push(slides[slides.length - 1 - i].cloneNode(true));
            }
            cloneStart.forEach(clone => {
                slideshowSlides.appendChild(clone);
            });
            cloneEnd.forEach(clone => {
                slideshowSlides.insertBefore(clone, slideshowSlides.children[0]);
            });
        };

        const handleImageLoading = () => {
            let i = imageArray.length;

            // possibly remove this setTimeout
            setTimeout(() => {
                if (i > 0) {
                    console.log("timed out, finishing setup");
                    finishSetup();
                }
            }, 8000);

            imageArray.forEach((img, index) => {
                // if image is cached, i--, else i-- when image finishes loading
                if (img.complete) {
                    i--;
                } else {
                    img.onload = function () {
                        i--;
                        if (i <= 0) {
                            finishSetup();
                        }
                    };
                }

                // wait until remaining images are loaded then finish setup
                if (i <= 0) {
                    finishSetup();
                }
            });
        };

        const finishSetup = () => {
            updateWidthArray();
            createThumbs();
            window.addEventListener("resize", function () {
                updateWidthArray();
                updateImage();
                updateThumbsWidth();
            });
            slideshowSlides.addEventListener("click", clickSlide);
            updateImage();
            console.log("SETUP COMPLETE, HAVE A NICE DAY");
        };

        const updateWidthArray = () => {
            imageArray.forEach((img, index) => {
                imageWidthArray[index] = img.width + imageGap;
            });
        };

        const updateThumbsWidth = () => {
            const visibleThumbs = imageArray.length;
            const thumbSize =
                window.getComputedStyle(slideshow).getPropertyValue("--thumb-size") * 1;
            const thumbsWidth =
                visibleThumbs * thumbSize + (visibleThumbs - 1) * imageGap;
            slideshow.style.setProperty("--thumbs-width", thumbsWidth);
        };

        const createThumbs = () => {
            const thumbsWrapper = document.createElement("div");
            const thumbsDiv = document.createElement("div");
            thumbsWrapper.classList.add("js-slideshow__thumbs-wrapper");
            thumbsDiv.classList.add("js-slideshow__thumbs");

            imageArray.forEach((img, index) => {
                const imageClone = img.cloneNode(true);
                const imageFigure = document.createElement("figure");
                imageFigure.classList.add("js-slideshow__thumb-image");
                imageFigure.addEventListener("click", () => {
                    whichImage = index;
                    detectOverlap();
                    updateImage();
                });
                imageFigure.appendChild(imageClone);
                thumbsDiv.appendChild(imageFigure);
            });

            thumbsWrapper.appendChild(thumbsDiv);
            slideshow.appendChild(thumbsWrapper);
            updateThumbsWidth();
        };

        const toggleTransition = val => {
            const offsetTime = val * slideTransition / 1000 + "s";
            slideshow.style.setProperty("--offset-time", offsetTime);
        };

        const toggleEventListeners = toggle => {
            if (toggle === 0) {
                slideshowSlides.removeEventListener("click", clickSlide);
                slideshowSlides.removeEventListener("touchstart", handleTouchStart, false);
                slideshowSlides.removeEventListener("touchmove", handleTouchMove, false);
            } else {
                slideshowSlides.addEventListener("click", clickSlide);
                slideshowSlides.addEventListener("touchstart", handleTouchStart, false);
                slideshowSlides.addEventListener("touchmove", handleTouchMove, false);
            }
        };

        const getImagePos = (arr, index) => {
            const modIndex = index % arr.length;

            if (modIndex > 0) {
                const leftArray = arr.slice(0, modIndex);
                return (
                    leftArray.reduce((total, amount) => total + amount) + arr[modIndex] * 0.5);

            } else {
                return arr[modIndex] * 0.5;
            }
        };

        const updateImage = () => {
            const thumbs = document.querySelectorAll(
                "#jsSlideshow .js-slideshow__thumb-image");

            clearInterval(autoUpdate);
            autoUpdate = setInterval(() => {
                incImage();
            }, slideSpeed);
            const newOffset = getImagePos(imageWidthArray, whichImage);
            slideshow.style.setProperty("--offset", newOffset + "px");
            thumbs.forEach(thumb => {
                thumb.classList.remove("js-slideshow__thumb-image--selected");
            });
            thumbs[whichImage].classList.add("js-slideshow__thumb-image--selected");
            thumbs[whichImage].scrollIntoView({
                behavior: "auto",
                block: "center",
                inline: "center"
            });

        };

        const slideshow = document.querySelector("#jsSlideshow");
        const slideshowSlides = slideshow.querySelector(".js-slideshow__slides");
        const slideSpeed = slideshow.dataset.speed * 1000;
        const slideTransition = slideshow.dataset.transition * 1000;
        const overlap = 3; // how many images are duplicated at beginning & end
        let whichImage = overlap;
        let autoUpdate = setInterval(() => {
            incImage();
        }, slideSpeed);
        createDopplegangers(overlap);
        const imageArray = slideshowSlides.querySelectorAll("img");
        const imageGap =
            window.getComputedStyle(slideshow).getPropertyValue("--image-gap") * 1;
        const imageWidthArray = [];
        handleImageLoading();
        toggleTransition(1);

        // swipe gestures from https://stackoverflow.com/questions/15084675/how-to-implement-swipe-gestures-for-mobile-devices
        slideshowSlides.addEventListener("touchstart", handleTouchStart, false);
        slideshowSlides.addEventListener("touchmove", handleTouchMove, false);
        var xDown = null;
        var yDown = null;

        function handleTouchStart(evt) {
            xDown = evt.touches[0].clientX;
            yDown = evt.touches[0].clientY;
        }

        function handleTouchMove(evt) {
            if (!xDown || !yDown) {
                return;
            }
            var xUp = evt.touches[0].clientX;
            var yUp = evt.touches[0].clientY;
            var xDiff = xDown - xUp;
            var yDiff = yDown - yUp;

            if (Math.abs(xDiff) > Math.abs(yDiff)) {
                /*most significant*/
                if (xDiff > 0) {
                    incImage(1);
                } else {
                    incImage(-1);
                }
            }

            xDown = null;
            yDown = null;
        }
    </script>

</body>

</html>