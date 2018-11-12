<?php

namespace AppBundle\Entity;

/**
 * Session
 */
class Session
{
    /**
     * @var int
     */
    private $id;

    /**
     * @var string
     */
    private $name;

    /**
     * @var \DateTime
     */
    private $date;

    /**
     * @var string
     */
    private $duration;

    /**
     * @var \stdClass
     */
    private $location;

    /**
     * @var int
     */
    private $currentNb;

    /**
     * @var int
     */
    private $maxNb;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set name
     *
     * @param string $name
     *
     * @return Session
     */
    public function setName($name)
    {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName()
    {
        return $this->name;
    }

    /**
     * Set date
     *
     * @param \DateTime $date
     *
     * @return Session
     */
    public function setDate($date)
    {
        $this->date = $date;

        return $this;
    }

    /**
     * Get date
     *
     * @return \DateTime
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * Set duration
     *
     * @param string $duration
     *
     * @return Session
     */
    public function setDuration($duration)
    {
        $this->duration = $duration;

        return $this;
    }

    /**
     * Get duration
     *
     * @return string
     */
    public function getDuration()
    {
        return $this->duration;
    }

    /**
     * Set location
     *
     * @param \stdClass $location
     *
     * @return Session
     */
    public function setLocation($location)
    {
        $this->location = $location;

        return $this;
    }

    /**
     * Get location
     *
     * @return \stdClass
     */
    public function getLocation()
    {
        return $this->location;
    }

    /**
     * Set currentNb
     *
     * @param integer $currentNb
     *
     * @return Session
     */
    public function setCurrentNb($currentNb)
    {
        $this->currentNb = $currentNb;

        return $this;
    }

    /**
     * Get currentNb
     *
     * @return int
     */
    public function getCurrentNb()
    {
        return $this->currentNb;
    }

    /**
     * Set maxNb
     *
     * @param integer $maxNb
     *
     * @return Session
     */
    public function setMaxNb($maxNb)
    {
        $this->maxNb = $maxNb;

        return $this;
    }

    /**
     * Get maxNb
     *
     * @return int
     */
    public function getMaxNb()
    {
        return $this->maxNb;
    }
}

